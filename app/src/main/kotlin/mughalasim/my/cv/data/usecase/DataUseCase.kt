package mughalasim.my.cv.data.usecase

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CompletableDeferred
import mughalasim.my.cv.data.models.ResponseModel
import mughalasim.my.cv.ui.adapter.AdapterType
import mughalasim.my.cv.ui.adapter.RecyclerData

class DataUseCase: BaseUseCase<DataUseCase.Output>() {

    sealed class Input {
        object Empty: Input()
    }

    sealed class Output {
        data class Data(val recyclerData: List<RecyclerData>): Output()
    }

    override suspend fun run(): BaseResult<Output, Exception> {

        try {
            val response = getDataFromServer() ?: return BaseResult.Failure(Exception())

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
            return BaseResult.Success(Output.Data(recyclerData))
        } catch (e: Exception) {
            return BaseResult.Failure(e)
        }
    }

    private suspend fun getDataFromServer(): ResponseModel?{
        val def = CompletableDeferred<ResponseModel?>()
        FirebaseDatabase.getInstance().reference.get().addOnSuccessListener{
            try {
                def.complete(it.getValue(ResponseModel::class.java))
            }catch (e: Exception){
                def.complete(null)
            }
        }.addOnFailureListener {
            def.complete(null)
        }
        return def.await()
    }
}