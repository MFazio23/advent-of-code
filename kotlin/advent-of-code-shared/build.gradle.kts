plugins {
    kotlin("jvm") version "1.9.21"
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