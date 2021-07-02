tasks.register("createJavaDoc", Javadoc::class){
    source = sourceSets.create("digitalTwinsAPI.src.main.java").java
}

dependencies {
    implementation("com.azure:azure-digitaltwins-core:1.0.3")
    implementation("com.azure:azure-identity:1.2.4")
    implementation("com.azure:azure-core-http-okhttp:1.6.1")
    implementation("org.apache.clerezza.ext:org.json.simple:0.4")
    testImplementation("junit:junit:4.13")
}

tasks.jacocoTestReport{
    dependsOn(tasks.test)
    reports{
        xml.required.set(true)
        html.required.set(true)
    }
}

tasks.withType<Test> {
    testLogging.showStandardStreams = true
    testLogging {
        showCauses = true
        showStackTraces = true
        showStandardStreams = true
        events(*org.gradle.api.tasks.testing.logging.TestLogEvent.values())
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        finalizedBy(tasks.jacocoTestReport)
    }
}
