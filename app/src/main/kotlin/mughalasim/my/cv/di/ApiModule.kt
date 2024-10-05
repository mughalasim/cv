package mughalasim.my.cv.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import cv.data.retrofit.ApiAdapterFactory
import cv.data.service.ApiService
import cv.domain.repositories.AppLoggerRepository
import mughalasim.my.cv.BuildConfig
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit

val apiModule =
    module {

        single<OkHttpClient>() {
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor { message ->
                        get<AppLoggerRepository>().log("Https: $message")
                    }.apply {
                        level = if (BuildConfig.DEBUG){
                            HttpLoggingInterceptor.Level.BODY
                        } else {
                            HttpLoggingInterceptor.Level.NONE
                        }
                    },
                )
                .build()
        }

        single<Converter.Factory> {
            @Suppress("JSON_FORMAT_REDUNDANT")
            kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                isLenient = true
            }.asConverterFactory(CONTENT_TYPE_APPLICATION.toMediaType())
        }

        single<Retrofit> {
            Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .client(get())
                .addConverterFactory(get())
                .addCallAdapterFactory(ApiAdapterFactory())
                .build()
        }

        single<ApiService> {
            val retrofit: Retrofit = get()
            retrofit.create(ApiService::class.java)
        }
    }

private const val CONTENT_TYPE_APPLICATION = "application/json"
