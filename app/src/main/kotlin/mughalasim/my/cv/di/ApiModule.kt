package mughalasim.my.cv.di

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import cv.data.retrofit.ApiResultAdapterFactory
import cv.data.service.ApiService
import mughalasim.my.cv.BuildConfig
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit

val apiModule =
    module {

        single<OkHttpClient> {
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor { message ->
                        Log.d("HTTP: ", "Http: $message")
                    }.apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    },
                )
                .build()
        }

        single<Converter.Factory> {
            @Suppress("JSON_FORMAT_REDUNDANT")
            kotlinx.serialization.json.Json {
                // By default Kotlin serialization will serialize all of the keys present in JSON object and throw an
                // exception if given key is not present in the Kotlin class. This flag allows to ignore JSON fields
                ignoreUnknownKeys = true
            }.asConverterFactory(CONTENT_TYPE_APPLICATION.toMediaType())
        }

        single<Retrofit> {
            @Suppress("JSON_FORMAT_REDUNDANT")
            Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .client(get())
                .addConverterFactory(
                    kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                        .asConverterFactory(CONTENT_TYPE_APPLICATION.toMediaType()),
                )
                .addCallAdapterFactory(ApiResultAdapterFactory())
                .build()
        }

        single<ApiService> {
            val retrofit: Retrofit = get()
            retrofit.create(ApiService::class.java)
        }
    }

private const val CONTENT_TYPE_APPLICATION = "application/json"
