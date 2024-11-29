plugins {
    kotlin("jvm") version "1.9.21"

    // Code coverage from Kover
    id("org.jetbrains.kotlinx.kover") version "0.8.3"
}

group = "dev.mfazio.aoc"

dependencies {
    implementation(Deps.fazioUtilsJvm)
    
    testImplementation(Deps.kotlinTest)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

kover {
    reports {
        filters {
            excludes {
                classes("dev.mfazio.aoc.shared.TestSandbox")
            }
        }
    }
}