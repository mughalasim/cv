package mughalasim.my.cv.di

import mughalasim.my.cv.ui.GeneralViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { GeneralViewModel(get()) }
}
