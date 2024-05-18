package mughalasim.my.cv.ui.screens.list

import androidx.compose.runtime.Immutable
import cv.domain.State
import cv.domain.entities.ResponseEntity
import cv.domain.usecase.DataUseCase
import cv.domain.usecase.SettingsUseCase
import mughalasim.my.cv.di.DI
import mughalasim.my.cv.services.Route
import mughalasim.my.cv.ui.screens.base.BaseAction
import mughalasim.my.cv.ui.screens.base.BaseState
import mughalasim.my.cv.ui.screens.base.BaseViewModel

class ListScreenViewModel(
    private val dataUseCase: DataUseCase,
    private val settingsUseCase: SettingsUseCase,
) : BaseViewModel<ListScreenViewModel.UiState, ListScreenViewModel.Action>(UiState.Loading) {
    suspend fun getData() {
        sendAction(Action.Loading)
        dataUseCase.getData().also { result ->
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

    fun getExpandListOnStartUp() = settingsUseCase.getExpandListOnStartUp()

    fun isVerticalOrientation() = settingsUseCase.getListOrientation()

    fun openSettings() = DI.serviceNavigation.open(route = Route.SettingsScreen)

    fun onBannerTapped(bannerName: String) = dataUseCase.onBannerTapped(bannerName)

    @Immutable
    sealed interface UiState : BaseState {
        data object Loading : UiState

        data class Error(val message: String) : UiState

        data class ResultsReceived(
            val responseEntity: ResponseEntity,
        ) : UiState
    }

    sealed interface Action : BaseAction<UiState> {
        data object Loading : Action {
            override fun reduce(state: UiState): UiState = UiState.Loading
        }

        data class ShowErrorMessage(val message: String) : Action {
            override fun reduce(state: UiState): UiState = UiState.Error(message)
        }

        data class ShowResults(val responseEntity: ResponseEntity) : Action {
            override fun reduce(state: UiState): UiState = UiState.ResultsReceived(responseEntity)
        }
    }
}
