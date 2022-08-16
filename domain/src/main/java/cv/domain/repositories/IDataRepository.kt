package cv.domain.repositories

import cv.domain.entities.ResponseEntity
import cv.domain.usecase.BaseResult

interface IDataRepository {
    suspend fun getDataFromServer(): BaseResult<ResponseEntity, Exception>
}