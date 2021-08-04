dependencies{
    implementation(project(":digitalTwinAPI"))
    implementation("com.sothawo:mapjfx:2.15.3")
}

application {
    mainClass.set("callcenterclient.Main")
}

apply(plugin = "com.github.johnrengelman.shadow")