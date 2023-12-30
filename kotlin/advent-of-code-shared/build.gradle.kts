plugins {
    kotlin("jvm") version "1.9.21"
}

group = "dev.mfazio.aoc"

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    implementation(Deps.fazioUtilsJvm)
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}