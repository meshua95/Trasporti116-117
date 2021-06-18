import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
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

repositories {
    mavenCentral()
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "Main"
    }
}

application{
    mainModule.set("Trasporti116-117.main")
    mainClass.set("Main")
}

javafx {
    version = "15.0.1"
    modules = listOf("javafx.controls", "javafx.fxml", "javafx.web")
}

buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("gradle.plugin.com.github.jengelman.gradle.plugins:shadow:7.0.0")
    }
}

apply(plugin = "com.github.johnrengelman.shadow")

dependencies {
    implementation(gradleApi()) // Implementation: available at compile and runtime, non transitive
    implementation("com.puppycrawl.tools:checkstyle:8.42")
    implementation("com.azure:azure-digitaltwins-core:1.0.3")
    implementation("com.azure:azure-identity:1.2.4")
    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("com.azure:azure-core-http-okhttp:1.6.1")
    implementation("com.sothawo:mapjfx:2.15.3")
    implementation("org.apache.clerezza.ext:org.json.simple:0.4")
    testImplementation("junit:junit:4.13")

    implementation("javax.vecmath", "vecmath", "1.5.2")
    implementation("org.apache.commons", "commons-csv", "1.8")

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
