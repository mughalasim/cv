package mughalasim.my.cv.ui.screens.list

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import cv.domain.DomainResult
import cv.domain.entities.LanguageEntity
import cv.domain.entities.ResponseEntity
import cv.domain.usecase.DataUseCase
import cv.domain.usecase.SettingsUseCase
import kotlinx.coroutines.launch
import mughalasim.my.cv.ui.screens.base.BaseAction
import mughalasim.my.cv.ui.screens.base.BaseState
import mughalasim.my.cv.ui.screens.base.BaseViewModel
import mughalasim.my.cv.ui.utils.ErrorCodeConverter

class ListScreenViewModel(
    private val dataUseCase: DataUseCase,
    private val settingsUseCase: SettingsUseCase,
    private val errorCodeConverter: ErrorCodeConverter,
) : BaseViewModel<ListScreenViewModel.UiState, ListScreenViewModel.Action>(UiState.Loading) {
    private var hasFetchedLanguage = false

    init {
        viewModelScope.launch {
            getLanguage()
        }
    }

    fun getData() {
        sendAction(Action.Loading)
        viewModelScope.launch {
            dataUseCase.getData().also { result ->
                when (result) {
                    is DomainResult.Error -> {
                        sendAction(Action.ShowErrorMessage(errorCodeConverter.getMessage(result.error)))
                    }
                    is DomainResult.Success -> {
                        sendAction(Action.DataReceived(result.data))
                    }
                }
            }
        }
    }

    suspend fun getLanguage(){
        if (hasFetchedLanguage) {
            getData()
            return
        }
        sendAction(Action.Loading)
        dataUseCase.getLanguage().also { result ->
            hasFetchedLanguage = true
            when (result) {
                is DomainResult.Error -> {
                    sendAction(Action.ShowErrorMessage(errorCodeConverter.getMessage(result.error)))
                }
                is DomainResult.Success -> {
                    sendAction(Action.LanguageReceived(result.data))
                    getData()
                }
            }
        }
    }

    fun getExpandListOnStartUp() = settingsUseCase.getExpandListOnStartUp()

    fun isVerticalOrientation() = settingsUseCase.getListOrientation()

    fun onBannerTapped(bannerName: String) = dataUseCase.onBannerTapped(bannerName)

    fun onLinkTapped(url: String) = dataUseCase.onLinkTapped(url)

    @Immutable
    sealed interface UiState : BaseState {
        data object Loading : UiState

        data class Error(val message: String) : UiState

        data class DataReceived(
            val responseEntity: ResponseEntity,
        ) : UiState

        data class LanguageReceived(
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

        data class DataReceived(val responseEntity: ResponseEntity) : Action {
            override fun reduce(state: UiState): UiState = UiState.DataReceived(responseEntity)
        }

        data class LanguageReceived(val languageEntity: LanguageEntity) : Action {
            override fun reduce(state: UiState): UiState = UiState.LanguageReceived(languageEntity)
        }
    }
}
