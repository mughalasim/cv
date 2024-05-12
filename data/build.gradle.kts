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
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")


    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.12.0"))
    //noinspection UseTomlInstead
    api("com.squareup.okhttp3:okhttp")
    //noinspection UseTomlInstead
    api("com.squareup.okhttp3:logging-interceptor")

    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))
    //noinspection UseTomlInstead
    api("com.google.firebase:firebase-database")
    //noinspection UseTomlInstead
    api("com.google.firebase:firebase-analytics")
    //noinspection UseTomlInstead
    api("com.google.firebase:firebase-crashlytics")

    // Testing -------------------------------------------------------------------------------------
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
