plugins {
    alias(libs.plugins.kapt)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.detekt)
    alias(libs.plugins.spotless)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "cv.data"
    compileSdk = libs.versions.appCompileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.appMinSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_21.toString()
    }

    detekt {
        toolVersion = libs.versions.detekt.get()
        config.setFrom(rootProject.file("detekt.yml"))
        buildUponDefaultConfig = false
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(libs.core.ktx)

    api(libs.retrofit)
    api(libs.retrofit2.kotlinx.serialization.converter)
    api(libs.kotlinx.serialization.json)

    api(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    api(libs.logging.interceptor)

    api(platform(libs.firebase.bom))
    api(libs.firebase.database)
    api(libs.firebase.analytics)
    api(libs.firebase.crashlytics)

    // android x keep annotation
    implementation(libs.annotation.jvm)

    // Testing -------------------------------------------------------------------------------------
    testImplementation(libs.junit)
}
