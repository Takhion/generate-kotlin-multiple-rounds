package me.eugeniomarletti.sample

import com.google.auto.service.AutoService
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.TypeElement

@AutoService(Processor::class)
class SampleProcessor : AbstractProcessor() {

    private val kaptKotlinGeneratedOption = "kapt.kotlin.generated"
    private lateinit var kaptKotlinGenerated: File

    private val sampleAnnotation = "me.eugeniomarletti.sample.SampleAnnotation"

    override fun getSupportedAnnotationTypes() = setOf(sampleAnnotation)

    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)
        kaptKotlinGenerated = File(processingEnv.options[kaptKotlinGeneratedOption])
    }

    override fun process(annotations: Set<TypeElement>, roundEnv: RoundEnvironment): Boolean {
        val annotation = annotations.firstOrNull { it.toString() == sampleAnnotation } ?: return false
        for (element in roundEnv.getElementsAnnotatedWith(annotation)) {
            val className = element.simpleName.toString()
            val `package` = processingEnv.elementUtils.getPackageOf(element).toString()
            generateClass(className, `package`)
        }
        return true
    }

    private fun generateClass(className: String, `package`: String) {
        val source = generateSourceSource(`package`, className)
        val relativePath = `package`.replace('.', File.separatorChar)
        val folder = File(kaptKotlinGenerated, relativePath).apply { mkdirs() }
        File(folder, "Generated$className.kt").writeText(source)
    }

    private fun generateSourceSource(`package`: String, className: String) =
        """
        package $`package`

        import javax.inject.Inject

        class Generated$className @Inject constructor() {
            inline fun test(log: (message: String) -> Unit) {
                log("The annotated class was $`package`.$className")
            }
        }

        """.trimIndent()
}