/*import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    application
    id("org.openjfx.javafxplugin") version "0.0.9"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

tasks {
    "shadowJar"(ShadowJar::class) {
        isZip64 = true
    }
}

application{
    mainModule.set("Trasporti116-117.callCenterClient.main")
    mainClass.set("Main")
}

javafx {
    version = "15.0.1"
    modules = listOf("javafx.controls", "javafx.fxml", "javafx.web")
}

dependencies {
    implementation("com.sothawo:mapjfx:2.15.3")
}*/
dependencies{
    implementation(project(":digitalTwinAPI"))
    implementation("com.sothawo:mapjfx:2.15.3")
}