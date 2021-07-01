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

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects{
    apply(plugin = "org.openjfx.javafxplugin")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.danilopianini.git-sensitive-semantic-versioning")
    apply(plugin = "org.gradle.jacoco")
    apply(plugin = "pl.droidsonroids.jacoco.testkit")
    apply(plugin = "org.gradle.application")
    apply(plugin = "org.gradle.java")
    apply(plugin = "com.github.johnrengelman.shadow")

    javafx {
        version = "15.0.1"
        modules = listOf("javafx.controls", "javafx.fxml", "javafx.web")
    }

    dependencies{
        implementation("org.slf4j:slf4j-api:2.0.0-alpha1")
    }

    gitSemVer{
        version = computeGitSemVer()
    }
}
