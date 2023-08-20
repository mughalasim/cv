package cv.domain.repositories

import cv.domain.State
import cv.domain.entities.LanguageEntity
import kotlinx.coroutines.flow.Flow

interface ILanguageRepository {
    fun getLanguageFromFirebase(): Flow<State<LanguageEntity>>
}