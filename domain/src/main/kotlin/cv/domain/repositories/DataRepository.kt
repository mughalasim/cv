package cv.domain.repositories

import cv.domain.DomainResult
import cv.domain.entities.ResponseEntity

interface DataRepository {
    suspend fun getData(): DomainResult<ResponseEntity>
}
