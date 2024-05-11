import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

val keystorePropertiesFile = rootProject.file("keystore.properties")
val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

android {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    val appName = libs.findVersion("appName").get().toString()

    namespace = libs.findVersion("appNamespaceId").get().toString()
    compileSdk = libs.findVersion("compileSdk").get().toString().toInt()

    defaultConfig {
        applicationId = libs.findVersion("appNamespaceId").get().toString()
        minSdk = libs.findVersion("minSdk").get().toString().toInt()
        targetSdk = libs.findVersion("compileSdk").get().toString().toInt()
        versionCode = libs.findVersion("appVersionCode").get().toString().toInt()
        versionName = libs.findVersion("appVersionName").get().toString()

        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        vectorDrawables { useSupportLibrary = true }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("config") {
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("config")
            resValue("string", "app_name", appName)
        }

        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
            resValue("string", "app_name", "$appName (Debug)")
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }

    flavorDimensions += "version"
    productFlavors {
        create("App") {
            dimension = "version"
            setProperty("archivesBaseName", appName)
        }
    }

    kapt {
        correctErrorTypes = true
    }

    packaging {
        resources.excludes +=
            setOf(
                "META-INF/AL2.0",
                "META-INF/licenses/**",
                "**/attach_hotspot_windows.dll",
                "META-INF/LGPL2.1",
            )
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(libs.kotlin.reflect)
    implementation(libs.core.ktx)

    implementation(libs.compiler)
    implementation(libs.ui.tooling.preview)
    debugImplementation(libs.ui.tooling)
    implementation(libs.material)
    implementation(libs.runtime.livedata)
    implementation(libs.foundation.android)

    implementation(libs.activity.compose)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.extensions)
    implementation(libs.accompanist.systemuicontroller)

// Navigation
    implementation(libs.navigation.compose)

// Koin Dependency Injection
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

// Coroutines
    implementation(libs.kotlinx.coroutines.android)

// Restring for Dynamic languages
    implementation(libs.restring)
    implementation(libs.viewpump)
    implementation(libs.reword)
    implementation(libs.applocale)
    implementation(libs.appcompat)

// TESTING -------------------------------------------------------------------------------------
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ui.test.junit4)
}
