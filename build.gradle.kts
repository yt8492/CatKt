plugins {
    kotlin("multiplatform") version "1.3.72"
}
repositories {
    mavenCentral()
    maven("https://kotlin.bintray.com/kotlinx")
}

kotlin {
    macosX64("macos") {
        binaries {
            executable {
                entryPoint = "com.yt8492.catkt.main"
                runTask?.args("")
            }
        }
        compilations["main"].enableEndorsedLibs = true
    }
    jvm()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-cli:0.2.1")
            }
        }
        val macosMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
    }
}