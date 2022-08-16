package cv.domain.usecase

import cv.domain.entities.AdapterType
import cv.domain.entities.RecyclerEntity
import cv.domain.repositories.IDataRepository

class DataUseCase(
    private val dataRepository: IDataRepository
) : BaseUseCase<List<RecyclerEntity>>() {

    override suspend fun run(): BaseResult<List<RecyclerEntity>, Exception> {

        try {
            when (val result = dataRepository.getDataFromServer()){
                is BaseResult.Success -> {
                    val response = result.successType

                    val recyclerData: MutableList<RecyclerEntity> = mutableListOf()

                    recyclerData.add(
                        RecyclerEntity(description = response.description, type = AdapterType.DESCRIPTION)
                    )

                    if (response.skills.isNotEmpty()) {
                        recyclerData.add(RecyclerEntity(title = "Skills", type = AdapterType.TITLE))
                        for (skill in response.skills) {
                            recyclerData.add(RecyclerEntity(skill = skill, type = AdapterType.SKILL))
                        }
                    }

                    if (response.works.isNotEmpty()) {
                        recyclerData.add(RecyclerEntity(title = "Work experience", type = AdapterType.TITLE))
                        for (work in response.works) {
                            recyclerData.add(RecyclerEntity(work = work, type = AdapterType.WORK))
                        }
                    }

                    if (response.educations.isNotEmpty()) {
                        recyclerData.add(RecyclerEntity(title = "Education", type = AdapterType.TITLE))
                        for (education in response.educations) {
                            recyclerData.add(RecyclerEntity(education = education, type = AdapterType.EDUCATION))
                        }
                    }

                    if (response.references.isNotEmpty()) {
                        recyclerData.add(RecyclerEntity(title = "References", type = AdapterType.TITLE))
                        for (reference in response.references) {
                            recyclerData.add(RecyclerEntity(reference = reference, type = AdapterType.REFERENCE))
                        }
                    }
                    return BaseResult.Success(recyclerData.toList())
                }

                is BaseResult.Failure -> {
                    return BaseResult.Failure(Exception())
                }
            }
        } catch (e: Exception) {
            return BaseResult.Failure(e)
        }
    }
}