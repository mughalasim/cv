# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# Keep `Companion` object fields of serializable classes.
# This avoids serializer lookup through `getDeclaredClasses` as done for named companion objects.

# Retrofit
-keep class retrofit2.** { *; }
-keepattributes Signature,RuntimeVisibleAnnotations,AnnotationDefault

# Retrofit with Kotlin
-keepattributes *Annotation*

# OkHttp
-keep class okhttp3.** { *; }
-keepattributes Signature

# kotlinx.serialization
-keep class kotlinx.serialization.** { *; }

# Retrofit with Kotlin coroutines
-keep class kotlinx.coroutines.** { *; }

# Custom CallAdapter.Factory
-keep class cv.data.retrofit.ApiResultAdapterFactory { *; }
-keep class **.ApiResult { *; }
