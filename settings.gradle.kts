rootProject.name = "CraftTools"

pluginManagement {
    val kotlinVersion: String by settings
    val gradleShadowPluginVersion: String by settings
    val gradleDownloadPluginVersion: String by settings
    val spotlessVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion
        id("com.github.johnrengelman.shadow") version gradleShadowPluginVersion
        id("de.undercouch.download") version gradleDownloadPluginVersion
        id("com.diffplug.spotless") version spotlessVersion
    }
}
