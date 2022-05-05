

plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow")
    id("de.undercouch.download")
    id("com.diffplug.spotless")
}

group = "jp.entdecken"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    // PaperMC
    maven("https://papermc.io/repo/repository/maven-public/")

    // CodeMC.io
    maven("https://repo.codemc.io/repository/maven-snapshots/")
}

dependencies {
    val paperAPIVersion: String by project
    val minecraftVersion: String by project

    implementation(kotlin("stdlib"))
    compileOnly("io.papermc.paper:paper-api:$paperAPIVersion")
    compileOnly(files("libs/paper-$minecraftVersion.jar"))
    testImplementation(kotlin("test"))
}
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    val ktlintVersion: String by project

    format("misc") {
        target("**/*.md", "**/*.yml", "**/.gitignore")
        indentWithSpaces()
        endWithNewline()
        trimTrailingWhitespace()
    }

    kotlin {
        target(
            fileTree(".") {
                include("**/*.kt")
                exclude("**/.gradle/**")
                exclude("**/build/**")
            }
        )

        ktlint(ktlintVersion)
        indentWithSpaces()
        endWithNewline()
        trimTrailingWhitespace()
    }

    kotlinGradle {
        target("*.gradle.kts")
        ktlint(ktlintVersion)
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks {
    build {
        dependsOn("shadowJar")
    }

    compileKotlin {
        kotlinOptions {
            jvmTarget = "17"
        }
    }

    processResources {
        dependsOn("ensurePatchedPaperJar")

        filteringCharset = "UTF-8"
        from(sourceSets["main"].resources.srcDirs) {
            include("**/*.yml")
            duplicatesStrategy = DuplicatesStrategy.INCLUDE
            filter<org.apache.tools.ant.filters.ReplaceTokens>("tokens" to mapOf("version" to project.version))
            filter<org.apache.tools.ant.filters.ReplaceTokens>("tokens" to mapOf("name" to project.name))
        }
    }

    register("ensurePatchedPaperJar") {
        group = "paper"
        description = "Ensure that patched Paper jar file is generated"

        doLast {
            val minecraftVersion: String by project
            val file = File("libs/paper-$minecraftVersion.jar")

            if (!file.exists()) {
                throw Exception("Patched Paper jar file is not exists. Generate it with 'copyPatchedPaperJar' task.")
            }
        }
    }

    register("ensureDirectories") {
        group = "paper"
        description = "Create directories for creating patched Paper jar."

        doLast {
            mkdir("libs")
            mkdir("temp")
        }
    }

    register<de.undercouch.gradle.tasks.download.Download>("downloadPaperclip") {
        group = "paper"
        description = "Download Paperclip from PaperMC.io"

        dependsOn("ensureDirectories")

        val minecraftVersion: String by project
        val paperBuild: String by project

        src("https://papermc.io/api/v2/projects/paper/versions/$minecraftVersion/builds/$paperBuild/downloads/paper-$minecraftVersion-$paperBuild.jar")
        dest(File("temp"))
        overwrite(false)
    }

    register<JavaExec>("runPaperclip") {
        group = "paper"
        description = "Run downloaded Paperclip."

        dependsOn("downloadPaperclip")

        val minecraftVersion: String by project
        val paperBuild: String by project

        classpath = files("temp/paper-$minecraftVersion-$paperBuild.jar")
        workingDir = File("temp")
        args("nogui")
    }

    register<Copy>("copyPatchedPaperJar") {
        group = "paper"
        description = "Copy patched Paper jar file to libs directory from temp directory."

        dependsOn("runPaperclip")

        val minecraftVersion: String by project

        from("temp/versions/$minecraftVersion/paper-$minecraftVersion.jar")
        into("libs")
    }
}
