plugins {
    alias(libs.plugins.crashlytics) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.spotless) apply false
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
}
