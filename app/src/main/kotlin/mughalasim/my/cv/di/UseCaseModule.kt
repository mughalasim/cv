package mughalasim.my.cv.di

import cv.domain.usecase.MainActivityUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { MainActivityUseCase(get(), get()) }
}
