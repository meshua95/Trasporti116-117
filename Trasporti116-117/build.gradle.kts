import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

version = project.version

plugins {
    jacoco //java code coverage
    java
    id("java")
    id("org.danilopianini.git-sensitive-semantic-versioning") version "0.2.3"
    id("pl.droidsonroids.jacoco.testkit") version "1.0.8"
    kotlin("jvm") version "1.4.10"
    application
    id("org.openjfx.javafxplugin") version "0.0.9"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

gitSemVer{
    version = computeGitSemVer()
}

tasks {
    "shadowJar"(ShadowJar::class) {
        isZip64 = true
    }
}

tasks.register("createJavaDoc", Javadoc::class){
    source = java.sourceSets.create("src.main.java.digitalTwinsAPI").java
}

repositories {
    mavenCentral()
}

application{
    mainModule.set("Trasporti116-117.main")
    mainClass.set("Main")
}

javafx {
    version = "15.0.1"
    modules = listOf("javafx.controls", "javafx.fxml", "javafx.web")
}

dependencies {
    implementation(gradleApi()) // Implementation: available at compile and runtime, non transitive
    implementation("com.azure:azure-digitaltwins-core:1.0.3")
    implementation("com.azure:azure-identity:1.2.4")
    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("com.azure:azure-core-http-okhttp:1.6.1")
    implementation("com.sothawo:mapjfx:2.15.3")
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
