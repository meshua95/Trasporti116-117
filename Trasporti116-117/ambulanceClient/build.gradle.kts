import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

dependencies{
    implementation(project(":digitalTwinAPI"))
}

tasks {
    "shadowJar"(ShadowJar::class) {
        isZip64 = true
    }
}

application{
    //mainModule.set("Trasporti-116117.ambulanceClient.main")
    mainClass.set("MainAmbulanceClient")
}