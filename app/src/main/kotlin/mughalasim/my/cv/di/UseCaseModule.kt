package mughalasim.my.cv.di

import cv.domain.usecase.DataUseCase
import cv.domain.usecase.LanguageUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single { DataUseCase(get()) }

    single { LanguageUseCase(get()) }

}
