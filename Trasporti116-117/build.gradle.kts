plugins {
    jacoco     //java code coverage
    java
    id("java")  //javadoc
    id("org.danilopianini.git-sensitive-semantic-versioning") version "0.2.3"
    id("pl.droidsonroids.jacoco.testkit") version "1.0.8"
    id("io.gitlab.arturbosch.detekt") version "1.17.0-RC2"
    kotlin("jvm") version "1.4.10"
    application
    id("org.openjfx.javafxplugin") version "0.0.9"
}

gitSemVer{
    version = computeGitSemVer()
}

repositories {
    mavenCentral()
}

javafx {
    modules = listOf("javafx.controls", "javafx.fxml", "javafx.web")
}

dependencies {
    implementation(gradleApi()) // Implementation: available at compile and runtime, non transitive
    implementation("com.puppycrawl.tools:checkstyle:8.42")
    implementation("com.azure:azure-digitaltwins-core:1.0.3")
    implementation("com.azure:azure-identity:1.2.4")
    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("org.slf4j:slf4j-simple:1.7.30")
    implementation("com.azure:azure-core-http-okhttp:1.6.1")
    testImplementation("junit:junit:4.13")
}

tasks.jacocoTestReport{
    reports{
        xml.isEnabled = true
        html.isEnabled = true
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
    }
}

/*
sourceSets{
    buildDir("src/main/java")
}

tasks.javadoc {
    //senza sorgente javadoc non crea nessuna documentazione
    source = sourceSets.main.allJava
}

*/

