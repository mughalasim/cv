package cv.domain.repositories

import cv.domain.State
import cv.domain.entities.LanguageEntity

interface ILanguageRepository {
    suspend fun getLanguage(): State<LanguageEntity>
}
