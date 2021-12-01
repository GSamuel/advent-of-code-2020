plugins {
    kotlin("jvm") version "1.6.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test-junit"))
}

tasks {
    sourceSets {
        main {
            //java.srcDirs("src")
        }
    }

    wrapper {
        gradleVersion = "7.3"
    }
}
