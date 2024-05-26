package cv.data.repository

import android.app.Application
import cv.data.models.toLanguageEntity
import cv.data.retrofit.ApiResult
import cv.data.retrofit.toDomainError
import cv.data.service.ApiService
import cv.domain.DomainResult
import cv.domain.entities.LanguageEntity
import cv.domain.repositories.LanguageRepository

class LanguageRepositoryImp(
    application: Application,
    private val apiService: ApiService,
) : LanguageRepository {
    private val locale = application.resources.configuration.locales.get(0).language

    override suspend fun getLanguage(): DomainResult<LanguageEntity> {
           return when (val response = apiService.getLanguage(locale)) {
                is ApiResult.Error -> {
                    DomainResult.Error(response.error.toDomainError())
                }

                is ApiResult.Success -> {
                    DomainResult.Success(response.data.toLanguageEntity(locale))
                }
            }
    }
}
