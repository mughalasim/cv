plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.detekt)
    alias(libs.plugins.spotless)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

detekt {
    toolVersion = libs.versions.detekt.get()
    config.setFrom(rootProject.file("detekt.yml"))
    buildUponDefaultConfig = false
}

dependencies {
    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
}
