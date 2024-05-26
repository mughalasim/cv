package cv.domain.repositories

import cv.domain.DomainResult
import cv.domain.entities.LanguageEntity

interface LanguageRepository {
    suspend fun getLanguage(): DomainResult<LanguageEntity>
}
