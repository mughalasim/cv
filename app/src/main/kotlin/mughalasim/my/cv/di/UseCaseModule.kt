package mughalasim.my.cv.di

import mughalasim.my.cv.data.usecase.DataUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single { DataUseCase() }

}
