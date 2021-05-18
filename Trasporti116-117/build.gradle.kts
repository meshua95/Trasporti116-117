 plugins {
     `java-gradle-plugin`
     jacoco
     id("org.jetbrains.kotlin.jvm") version "1.5.0-RC"
     id("org.jetbrains.dokka") version "1.4.30"
     id("org.danilopianini.git-sensitive-semantic-versioning") version "0.2.3"
     id("com.gradle.plugin-publish") version "0.14.0"
     id("pl.droidsonroids.jacoco.testkit") version "1.0.8"
     id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
     id("io.gitlab.arturbosch.detekt") version "1.17.0-RC2"
}
gitSemVer{
    version = computeGitSemVer()
}

group = "org.meshuagalassi"

repositories {
    mavenCentral()
}

dependencies {
    implementation(gradleApi()) // Implementation: available at compile and runtime, non transitive
    implementation("com.azure:azure-digitaltwins-core:1.0.3")
    implementation("com.azure:azure-identity:1.2.4")
    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("org.slf4j:slf4j-simple:1.7.30")
    implementation("org.apache.clerezza.ext:org.json.simple:0.4")
    implementation("com.azure:azure-core-http-okhttp:1.6.1")
}
