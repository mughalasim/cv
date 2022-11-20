package cv.domain.usecase

import cv.domain.repositories.IDataRepository

class DataUseCase(
    private val dataRepository: IDataRepository
) : BaseUseCase {

    // Apply any filters here or save to the Local Database at this point
    override fun getData() = dataRepository.fetchDataFromFirebase()
}