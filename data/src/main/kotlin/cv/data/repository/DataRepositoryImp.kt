package cv.data.repository

import cv.data.models.toResponseEntity
import cv.data.retrofit.ApiResult
import cv.data.retrofit.toDomainError
import cv.data.service.ApiService
import cv.domain.DomainResult
import cv.domain.repositories.DataRepository

class DataRepositoryImp(
    private val apiService: ApiService,
) : DataRepository {
    override suspend fun getData() =
        when (val response = apiService.getData()) {
            is ApiResult.Error -> {
                DomainResult.Error(response.error.toDomainError())
            }

            is ApiResult.Success -> {
                DomainResult.Success(response.data.toResponseEntity())
            }
        }
}
