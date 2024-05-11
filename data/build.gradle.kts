plugins {
    kotlin("kapt")
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.detekt)
    alias(libs.plugins.spotless)
}

android {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    namespace = "cv.data"
    compileSdk = libs.findVersion("compileSdk").get().toString().toInt()

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

    // Import the BoM for the Firebase platform
    api(platform("com.google.firebase:firebase-bom:33.0.0"))

    // Declare the dependency for the Realtime Database library
    // When using the BoM, you don"t specify versions in Firebase library dependencies
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
