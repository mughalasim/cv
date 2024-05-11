plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    // Kotlin coroutines
    api(libs.kotlinx.coroutines.android)
    api(libs.kotlinx.coroutines.core)

    // Arrow - Functional programming
    api(libs.arrow.core)

    // Joda time
    api(libs.android.joda)

    // android x keep annotation
    api(libs.annotation.jvm)
}
