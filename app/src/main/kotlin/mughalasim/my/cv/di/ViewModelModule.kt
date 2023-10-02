package mughalasim.my.cv.di

import mughalasim.my.cv.ui.screens.list.ListScreenViewModel
import mughalasim.my.cv.ui.screens.main.MainScreenViewModel
import mughalasim.my.cv.ui.screens.settings.SettingsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { ListScreenViewModel(get(), get()) }

    viewModel { MainScreenViewModel(get()) }

    viewModel { SettingsScreenViewModel(get()) }

}
