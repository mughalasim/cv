package mughalasim.my.cv.ui.screens.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

open class BaseViewModel<State : BaseState, Action : BaseAction<State>>(initialViewState: State) : ViewModel() {
    private val _uiStateFlow = MutableStateFlow(initialViewState)
    val uiStateFlow = _uiStateFlow.asStateFlow()

    private var viewState by Delegates.observable(initialViewState) { _, old, new ->
        if (old != new) {
            viewModelScope.launch {
                _uiStateFlow.value = new
            }
        }
    }

    fun sendAction(action: Action) {
        viewState = action.reduce(viewState)
    }
}
