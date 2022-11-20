package cv.domain.repositories

import cv.domain.State
import cv.domain.entities.ResponseEntity
import kotlinx.coroutines.flow.Flow

interface IDataRepository {
    fun fetchDataFromFirebase(): Flow<State<ResponseEntity>>
}