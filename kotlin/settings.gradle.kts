@file:Suppress("UnstableApiUsage")

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

rootProject.name = "advent-of-code-kotlin"
include("advent-of-code-2023")
include("advent-of-code-shared")
include("advent-of-code-dependencies")
