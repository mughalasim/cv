package mughalasim.my.cv.di

import com.google.firebase.database.FirebaseDatabase
import cv.data.repository.DataRepository
import cv.data.repository.LanguageRepository
import cv.data.repository.SettingsRepository
import cv.domain.repositories.IDataRepository
import cv.domain.repositories.ILanguageRepository
import cv.domain.repositories.ISettingsRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val repositoryModule = module {

    val firebaseInstance = FirebaseDatabase.getInstance()

    single<IDataRepository> { DataRepository(firebaseInstance) }

    single<ILanguageRepository> { LanguageRepository(androidApplication(), firebaseInstance) }

    single<ISettingsRepository> { SettingsRepository(get()) }
}