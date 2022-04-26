import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20"
}

group = "jp.entdecken"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    // PaperMC
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    val paperAPIVersion: String by project
    val minecraftVersion: String by project

    compileOnly("io.papermc.paper:paper-api:$paperAPIVersion")
    compileOnly(files("libs/paper-$minecraftVersion.jar"))
    testImplementation(kotlin("test"))
}
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}