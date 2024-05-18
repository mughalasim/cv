package mughalasim.my.cv.ui.screens.main

import androidx.compose.runtime.Immutable
import androidx.navigation.NavHostController
import cv.domain.State
import cv.domain.entities.LanguageEntity
import cv.domain.usecase.DataUseCase
import mughalasim.my.cv.di.DI
import mughalasim.my.cv.services.ServiceNavigation
import mughalasim.my.cv.ui.screens.base.BaseAction
import mughalasim.my.cv.ui.screens.base.BaseState
import mughalasim.my.cv.ui.screens.base.BaseViewModel

class MainScreenViewModel(
    private val dataUseCase: DataUseCase,
) : BaseViewModel<MainScreenViewModel.UiState, MainScreenViewModel.Action>(UiState.Loading) {
    suspend fun getLanguage(){
        sendAction(Action.Loading)
        dataUseCase.getLanguage().also { result ->
            when (result) {
                is State.Failed -> {
                    sendAction(Action.ShowErrorMessage(result.message))
                }
                is State.Success -> {
                    sendAction(Action.ShowResults(result.data))
                }
            }
        }
    }

    fun setNavController(navController: NavHostController) = DI.serviceNavigation.setNavController(navController)

    fun getServiceNavigation(): ServiceNavigation = DI.serviceNavigation

    @Immutable
    sealed interface UiState : BaseState {
        data object Loading : UiState

        data class Error(val message: String) : UiState

        data class ResultsReceived(
            val languageEntity: LanguageEntity,
        ) : UiState
    }

    sealed interface Action : BaseAction<UiState> {
        data object Loading : Action {
            override fun reduce(state: UiState): UiState = UiState.Loading
        }

        data class ShowErrorMessage(val message: String) : Action {
            override fun reduce(state: UiState): UiState = UiState.Error(message)
        }

        data class ShowResults(val languageEntity: LanguageEntity) : Action {
            override fun reduce(state: UiState): UiState = UiState.ResultsReceived(languageEntity)
        }
    }
}
