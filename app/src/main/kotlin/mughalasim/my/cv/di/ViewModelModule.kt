package mughalasim.my.cv.di

import mughalasim.my.cv.ui.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainScreenViewModel(get()) }
}
