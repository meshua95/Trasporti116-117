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
    pmd
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
    apply(plugin = "pmd")
    apply(plugin = "java")

    javafx {
        version = "15.0.1"
        modules = listOf("javafx.controls", "javafx.fxml", "javafx.web")
    }

    gitSemVer{
        version = computeGitSemVer()
    }

    tasks.withType<Checkstyle> {
        ignoreFailures = true
        reports {
            xml.required.set(true)
            html.required.set(true)
        }
    }

    pmd {
        toolVersion = "6.37.0"
        rulesMinimumPriority.set(4)
        ruleSets = listOf("category/java/errorprone.xml", "category/java/bestpractices.xml")
        isIgnoreFailures = true
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>{
        kotlinOptions.allWarningsAsErrors = true
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
}
