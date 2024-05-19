package cv.data.repository

import cv.data.models.toResponseEntity
import cv.data.retrofit.ApiResult
import cv.data.service.ApiService
import cv.domain.State
import cv.domain.repositories.IDataRepository

class DataRepository(
    private val apiService: ApiService,
) : IDataRepository {
    override suspend fun getData() =
        when (val response = apiService.getData()) {
            is ApiResult.Error -> {
                State.Failed(response.message ?: "")
            }

            is ApiResult.Exception -> {
                State.Failed(response.throwable.message ?: "")
            }

            is ApiResult.Success -> {
                State.Success(response.data.toResponseEntity())
            }
        }
}
