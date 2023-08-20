package cv.domain.usecase

import cv.domain.repositories.IDataRepository
import cv.domain.repositories.ILanguageRepository

class DataUseCase(
    private val dataRepository: IDataRepository,
    private val languageRepository: ILanguageRepository
) {
    fun getData() = dataRepository.getDataFromFirebase()

    fun getLanguage() = languageRepository.getLanguageFromFirebase()

}