package mughalasim.my.cv.di

import cv.data.repository.DataRepositoryImp
import cv.data.repository.LanguageRepositoryImp
import cv.data.repository.SettingsRepositoryImp
import cv.domain.repositories.DataRepository
import cv.domain.repositories.LanguageRepository
import cv.domain.repositories.SettingsRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val repositoryModule =
    module {
        single<DataRepository> { DataRepositoryImp(get()) }

        single<LanguageRepository> { LanguageRepositoryImp(androidApplication(), get()) }

        single<SettingsRepository> { SettingsRepositoryImp(get()) }
    }
