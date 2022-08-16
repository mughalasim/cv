package mughalasim.my.cv.di

import cv.data.repository.DataRepository
import cv.domain.repositories.IDataRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IDataRepository> { DataRepository() }
}