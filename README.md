### Sample created for the article ["Pushing the limits of Kotlin annotation processing"](https://medium.com/@workingkills/pushing-the-limits-of-kotlin-annotation-processing-8611027b6711).
---

This sample Android project demonstrates:

1. Generating Kotlin code though an [annotation processor](/processor/src/main/java/me/eugeniomarletti/sample/SampleProcessor.kt).
2. Feeding that generated code into a second round of annotation processing by putting it into a [Gradle module](/module/build.gradle#L29) and consuming it into [another one](/app/build.gradle#L26).
3. Testing that a second round of processing [has happened](/app/src/main/java/me/eugeniomarletti/sample/Sample.kt#L10). In this case [Dagger 2](/app/build.gradle#L29) (used as an example) is consuming the previously generated code to generate additional classes.
