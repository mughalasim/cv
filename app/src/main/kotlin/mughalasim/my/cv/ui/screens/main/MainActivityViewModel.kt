package mughalasim.my.cv.ui.screens.main

import androidx.lifecycle.ViewModel
import cv.domain.usecase.DataUseCase

class MainActivityViewModel(
    private val dataUseCase: DataUseCase
) : ViewModel() {

    fun getData() = dataUseCase.getData()

}