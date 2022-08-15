package mughalasim.my.cv.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import mughalasim.my.cv.ui.adapter.RecyclerData
import mughalasim.my.cv.domain.usecase.DataUseCase

class GeneralViewModel(
    private val dataUseCase: DataUseCase
) : ViewModel() {

    private val _data = MutableLiveData<List<RecyclerData>>()
    val data: LiveData<List<RecyclerData>> = _data

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