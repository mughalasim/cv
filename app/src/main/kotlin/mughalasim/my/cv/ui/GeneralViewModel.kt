package mughalasim.my.cv.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cv.domain.entities.RecyclerEntity
import cv.domain.usecase.DataUseCase

class GeneralViewModel(
    private val dataUseCase: DataUseCase
) : ViewModel() {

    private val _data = MutableLiveData<List<RecyclerEntity>>()
    val data: LiveData<List<RecyclerEntity>> = _data

    private val _showMessage = MutableLiveData<Boolean>()
    val showMessage: LiveData<Boolean> = _showMessage

    fun getRecyclerData() {
        dataUseCase.invoke(viewModelScope) {
            it.result(onSuccess = { response ->
                _data.postValue((response))
                _showMessage.postValue(false)
            }, onFailure = {
                _showMessage.postValue(true)
            })
        }
    }

}