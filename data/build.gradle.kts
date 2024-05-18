plugins {
    kotlin("kapt")
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.detekt)
    alias(libs.plugins.spotless)
    kotlin("plugin.serialization")
}

android {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    namespace = "cv.data"
    compileSdk = libs.findVersion("compileSdk").get().toString().toInt()

    defaultConfig {
        minSdk = libs.findVersion("minSdk").get().toString().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
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

    // Testing -------------------------------------------------------------------------------------
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
