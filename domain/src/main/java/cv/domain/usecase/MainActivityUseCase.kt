package cv.domain.usecase

import cv.domain.repositories.IDataRepository
import cv.domain.repositories.ILanguageRepository

class MainActivityUseCase(
    private val dataRepository: IDataRepository,
    private val languageRepository: ILanguageRepository
) {
    fun getData() = dataRepository.fetchDataFromFirebase()

    fun getLanguage() = languageRepository.fetchLanguageFromFirebase()
}