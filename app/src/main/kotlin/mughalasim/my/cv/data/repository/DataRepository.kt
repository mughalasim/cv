package mughalasim.my.cv.data.repository

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CompletableDeferred
import mughalasim.my.cv.data.models.ResponseModel
import mughalasim.my.cv.domain.repository.IDataRepository
import mughalasim.my.cv.domain.usecase.BaseResult

class DataRepository: IDataRepository {

    override suspend fun getDataFromServer(): BaseResult<ResponseModel, Exception>{
        val def = CompletableDeferred<BaseResult<ResponseModel, Exception>>()
        FirebaseDatabase.getInstance().reference.get().addOnSuccessListener{
            try {
                def.complete(BaseResult.Success(it.getValue(ResponseModel::class.java)!!))
            }catch (e: Exception){
                def.complete(BaseResult.Failure(e))
            }
        }.addOnFailureListener {
            def.complete(BaseResult.Failure(Exception()))
        }
        return def.await()
    }
}