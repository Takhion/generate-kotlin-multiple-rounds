package me.eugeniomarletti.sample

import dagger.Component

@Component interface MainComponent {
    val generated: GeneratedSampleClass
}

fun testGeneratedCode(log: (message: String) -> Unit) {
    DaggerMainComponent.create().generated.test(log)
}
