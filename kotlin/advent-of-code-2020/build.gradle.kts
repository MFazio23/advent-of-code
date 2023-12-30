plugins {
    kotlin("jvm") version "1.9.21"
}

group = "dev.mfazio.aoc"
version = "1.0.0-SNAPSHOT"

dependencies {
    implementation(project(":advent-of-code-shared"))
    implementation(Deps.fazioUtilsJvm)

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}