package mughalasim.my.cv.domain.repository

import mughalasim.my.cv.data.models.ResponseModel
import mughalasim.my.cv.domain.usecase.BaseResult

interface IDataRepository {
    suspend fun getDataFromServer(): BaseResult<ResponseModel, Exception>
}