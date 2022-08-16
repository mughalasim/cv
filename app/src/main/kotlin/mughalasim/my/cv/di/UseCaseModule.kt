package mughalasim.my.cv.di

import cv.domain.usecase.DataUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { DataUseCase(get()) }
}
