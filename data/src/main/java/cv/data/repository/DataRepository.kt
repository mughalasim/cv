package cv.data.repository

import com.google.firebase.database.*
import cv.domain.State
import cv.domain.entities.ResponseEntity
import cv.domain.repositories.IDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn

class DataRepository(firebaseInstance: FirebaseDatabase): IDataRepository {

    private var dbReference: DatabaseReference = firebaseInstance.getReference("/data")

    override fun fetchDataFromFirebase() = callbackFlow<State<ResponseEntity>> {
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    trySendBlocking(State.Success(snapshot.getValue(ResponseEntity::class.java)!!))
                } catch (e: Exception) {
                    trySendBlocking(State.Failed("FAILED"))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                trySendBlocking(State.Failed(error.message))
            }
        }

        dbReference.addValueEventListener(valueEventListener)

        dbReference.get()

        awaitClose {
            dbReference.removeEventListener(valueEventListener)
        }
    }.flowOn(Dispatchers.IO)
}