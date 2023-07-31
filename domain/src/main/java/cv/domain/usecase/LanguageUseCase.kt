package cv.domain.usecase

import cv.domain.repositories.ILanguageRepository

class LanguageUseCase(
    private val languageRepository: ILanguageRepository
) {
    fun getLanguage() = languageRepository.fetchLanguageFromFirebase()
}