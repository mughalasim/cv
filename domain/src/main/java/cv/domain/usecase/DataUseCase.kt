package cv.domain.usecase

import cv.domain.entities.ResponseEntity
import cv.domain.repositories.IDataRepository

class DataUseCase(
    private val dataRepository: IDataRepository
) : BaseUseCase<ResponseEntity>() {

    override suspend fun run(): BaseResult<ResponseEntity, Exception> {

        return try {
            when (val result = dataRepository.getDataFromServer()){
                is BaseResult.Success -> {
                    BaseResult.Success(result.successType)
                }
                is BaseResult.Failure -> {
                    BaseResult.Failure(Exception())
                }
            }
        } catch (e: Exception) {
            BaseResult.Failure(e)
        }
    }
}