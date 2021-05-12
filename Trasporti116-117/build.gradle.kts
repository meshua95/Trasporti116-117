plugins {
    `java-gradle-plugin`
    jacoco
    kotlin("jvm") version "14.0.1"
    id("org.jetbrains.dokka")
    id("org.danilopianini.git-sensitive-semantic-versioning")
    id("com.gradle.plugin-publish")
    id("pl.droidsonroids.jacoco.testkit")
    id("org.jlleitschuh.gradle.ktlint")
    id("io.gitlab.arturbosch.detekt")
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
