package cv.data.repository

import android.app.Application
import cv.data.models.toLanguageEntity
import cv.data.retrofit.ApiResult
import cv.data.service.ApiService
import cv.domain.State
import cv.domain.entities.LanguageEntity
import cv.domain.repositories.LanguageRepository

class LanguageRepositoryImp(
    application: Application,
    private val apiService: ApiService,
) : LanguageRepository {
    private val locale = application.resources.configuration.locales.get(0).language
    var languageEntity: LanguageEntity? = null

    override suspend fun getLanguage(): State<LanguageEntity> {
        if (languageEntity == null){
           return when (val response = apiService.getLanguage(locale)) {
                is ApiResult.Error -> {
                    State.Failed(response.message ?: "")
                }

                is ApiResult.Exception -> {
                    State.Failed(response.throwable.message ?: "")
                }

                is ApiResult.Success -> {
                    languageEntity = response.data.toLanguageEntity(locale)
                    State.Success(languageEntity!!)
                }
            }
        } else {
            return State.Success(languageEntity!!)
        }
    }
}
