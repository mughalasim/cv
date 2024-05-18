package mughalasim.my.cv.di

import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import cv.data.repository.AnalyticsRepository
import cv.data.repository.DataRepository
import cv.data.repository.LanguageRepository
import cv.data.repository.SettingsRepository
import cv.domain.repositories.IAnalyticsRepository
import cv.domain.repositories.IDataRepository
import cv.domain.repositories.ILanguageRepository
import cv.domain.repositories.ISettingsRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val repositoryModule =
    module {
        single<IDataRepository> { DataRepository(get()) }

        single<IAnalyticsRepository> { AnalyticsRepository(Firebase.analytics) }

        single<ILanguageRepository> { LanguageRepository(androidApplication(), get()) }

        single<ISettingsRepository> { SettingsRepository(get()) }
    }
