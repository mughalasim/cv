package mughalasim.my.cv.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cv.domain.entities.ResponseEntity
import cv.domain.usecase.DataUseCase

class MainScreenViewModel(
    private val dataUseCase: DataUseCase
) : ViewModel() {

    data class VmData (
        val state: State = State.LOADING,
        val data: ResponseEntity = ResponseEntity(),
        val errorMessage: String = ""
    )

    enum class State {
        LOADING, LIST, ERROR
    }

    private var currentVmData = VmData()
    private val _vmData = MutableLiveData(currentVmData)
    val vmData: LiveData<VmData> = _vmData


    fun fetchData() {

        currentVmData = currentVmData.copy(
            state = State.LOADING
        )
        _vmData.value = currentVmData

        dataUseCase.invoke(viewModelScope) { useCaseResponse ->
            useCaseResponse.result(
                onSuccess = {
                    currentVmData = currentVmData.copy(
                        state = State.LIST,
                        data = it
                    )
                    _vmData.value = currentVmData
                },
                onFailure = {
                    currentVmData = currentVmData.copy(
                        state = State.ERROR,
                        errorMessage = it.message ?: it.toString()
                    )
                    _vmData.value = currentVmData
                }
            )
        }
    }

}