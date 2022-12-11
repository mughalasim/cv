package cv.domain.usecase

import cv.domain.repositories.IDataRepository

class DataUseCase(
    private val dataRepository: IDataRepository
) : BaseUseCase {

    override fun getData() = dataRepository.fetchDataFromFirebase()
}