package cv.data.repository

import com.google.firebase.database.FirebaseDatabase
import cv.domain.entities.ResponseEntity
import cv.domain.repositories.IDataRepository
import cv.domain.usecase.BaseResult
import kotlinx.coroutines.CompletableDeferred

class DataRepository: IDataRepository {

    override suspend fun getDataFromServer(): BaseResult<ResponseEntity, Exception> {
        val def = CompletableDeferred<BaseResult<ResponseEntity, Exception>>()
        FirebaseDatabase.getInstance().reference.get().addOnSuccessListener{
            try {
                def.complete(BaseResult.Success(it.getValue(ResponseEntity::class.java)!!))
            }catch (e: Exception){
                def.complete(BaseResult.Failure(e))
            }
        }.addOnFailureListener {
            def.complete(BaseResult.Failure(Exception()))
        }
        return def.await()
    }
}