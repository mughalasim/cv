package cv.domain.repositories

import cv.domain.State
import cv.domain.entities.LanguageEntity

interface LanguageRepository {
    suspend fun getLanguage(): State<LanguageEntity>
}
