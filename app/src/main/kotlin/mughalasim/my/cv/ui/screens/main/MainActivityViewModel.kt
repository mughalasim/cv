package mughalasim.my.cv.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cv.domain.usecase.MainActivityUseCase
import dev.b3nedikt.restring.Restring
import kotlinx.coroutines.launch
import java.util.*

class MainActivityViewModel(
    private val useCase: MainActivityUseCase,
) : ViewModel() {

    fun getData() = useCase.getData()

    fun getLanguage() = useCase.getLanguage()

}