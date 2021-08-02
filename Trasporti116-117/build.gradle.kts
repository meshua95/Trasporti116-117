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
    checkstyle
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
    apply(plugin = "checkstyle")


    javafx {
        version = "15.0.1"
        modules = listOf("javafx.controls", "javafx.fxml", "javafx.web")
    }

    gitSemVer{
        version = computeGitSemVer()
    }

    tasks.withType<Checkstyle>().configureEach {
        reports {
            xml.required.set(true)
            html.required.set(true)
        }
    }
}
