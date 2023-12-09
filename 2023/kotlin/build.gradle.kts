plugins {
    kotlin("jvm") version "1.9.20"
}

group = "dev.mfazio.aoc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://jitpack.io")
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("dev.mfazio:fazio-utils-jvm:1.0.7")

    testImplementation(platform("org.junit:junit-bom:5.9.0"))
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

kotlin {
    jvmToolchain(8)
}