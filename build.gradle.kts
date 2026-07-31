plugins {
    kotlin("jvm") version "2.4.10"
}

group = "ru.technicalExcellence.codingDojo"
version = "1.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

val junitVersion: String = providers.gradleProperty("junitVersion").get()
val junitLauncherVersion: String = providers.gradleProperty("junitLauncherVersion").get()
val mockitoVersion: String = providers.gradleProperty("mockitoVersion").get()
val mockitoKotlinVersion: String = providers.gradleProperty("mockitoKotlinVersion").get()

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")

    testImplementation("org.mockito:mockito-core:$mockitoVersion")
    testImplementation("org.mockito.kotlin:mockito-kotlin:$mockitoKotlinVersion")
    testImplementation("org.mockito:mockito-junit-jupiter:$mockitoVersion")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher:$junitLauncherVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
