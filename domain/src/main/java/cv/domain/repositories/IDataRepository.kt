package cv.domain.repositories

import cv.domain.State
import cv.domain.entities.ResponseEntity

interface IDataRepository {
    suspend fun getDataFromFirebase(): State<ResponseEntity>
}
