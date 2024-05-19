package cv.domain.repositories

import cv.domain.State
import cv.domain.entities.ResponseEntity

interface DataRepository {
    suspend fun getData(): State<ResponseEntity>
}
