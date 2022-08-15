package mughalasim.my.cv.di

import mughalasim.my.cv.data.repository.DataRepository
import mughalasim.my.cv.domain.repository.IDataRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IDataRepository> { DataRepository() }
}