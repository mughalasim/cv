package cv.data.service

import cv.data.models.DescriptionModel
import cv.data.models.LanguageModel
import cv.data.models.ResponseModel
import cv.data.retrofit.ApiResult
import retrofit2.http.GET

interface ApiService {
    @GET("data.json")
    suspend fun getData(): ApiResult<ResponseModel>

    @GET("data/description.json")
    suspend fun getDescription(): ApiResult<DescriptionModel>

    @GET("language.json")
    suspend fun getLanguage(): ApiResult<LanguageModel>
}