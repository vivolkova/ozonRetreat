rootProject.name = "coding-dojo-kotlin"

pluginManagement {
    val kotlinVersion: String by settings

    repositories {
        mavenCentral()
    }

    plugins {
        kotlin("jvm") version kotlinVersion
    }
}
