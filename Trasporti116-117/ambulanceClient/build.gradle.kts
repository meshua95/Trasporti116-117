dependencies{
    implementation(project(":digitalTwinAPI"))
}

application {
    mainClass.set("ambulanceclient.Main")
}

apply(plugin = "com.github.johnrengelman.shadow")