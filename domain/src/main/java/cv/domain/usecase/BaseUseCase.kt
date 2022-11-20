package cv.domain.usecase

import cv.domain.State
import cv.domain.entities.ResponseEntity
import kotlinx.coroutines.flow.Flow

interface BaseUseCase{
    fun getData(): Flow<State<ResponseEntity>>
}
