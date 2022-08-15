package mughalasim.my.cv.domain.usecase

import mughalasim.my.cv.domain.repository.IDataRepository
import mughalasim.my.cv.ui.adapter.AdapterType
import mughalasim.my.cv.ui.adapter.RecyclerData

class DataUseCase(
    private val dataRepository: IDataRepository
) : BaseUseCase<List<RecyclerData>>() {

    override suspend fun run(): BaseResult<List<RecyclerData>, Exception> {

        try {
            when (val result = dataRepository.getDataFromServer()){
                is BaseResult.Success -> {
                    val response = result.successType

                    val recyclerData: MutableList<RecyclerData> = mutableListOf()

                    recyclerData.add(
                        RecyclerData(description = response.description, type = AdapterType.DESCRIPTION)
                    )

                    if (response.skills.isNotEmpty()) {
                        recyclerData.add(RecyclerData(title = "Skills", type = AdapterType.TITLE))
                        for (skill in response.skills) {
                            recyclerData.add(RecyclerData(skill = skill, type = AdapterType.SKILL))
                        }
                    }

                    if (response.works.isNotEmpty()) {
                        recyclerData.add(RecyclerData(title = "Work experience", type = AdapterType.TITLE))
                        for (work in response.works) {
                            recyclerData.add(RecyclerData(work = work, type = AdapterType.WORK))
                        }
                    }

                    if (response.educations.isNotEmpty()) {
                        recyclerData.add(RecyclerData(title = "Education", type = AdapterType.TITLE))
                        for (education in response.educations) {
                            recyclerData.add(RecyclerData(education = education, type = AdapterType.EDUCATION))
                        }
                    }

                    if (response.references.isNotEmpty()) {
                        recyclerData.add(RecyclerData(title = "References", type = AdapterType.TITLE))
                        for (reference in response.references) {
                            recyclerData.add(RecyclerData(reference = reference, type = AdapterType.REFERENCE))
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