package mughalasim.my.cv.di

import cv.domain.usecase.DataUseCase
import cv.domain.usecase.SettingsUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single { DataUseCase(get(), get()) }

    single { SettingsUseCase(get()) }

}
