package cv.data.repository

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import cv.domain.State
import cv.domain.entities.ResponseEntity
import cv.domain.repositories.IDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn

class DataRepository(firebaseInstance: FirebaseDatabase) : IDataRepository {
    private var firebaseReference: DatabaseReference = firebaseInstance.getReference("/data")

    override fun getDataFromFirebase() =
        callbackFlow<State<ResponseEntity>> {
            val valueEventListener =
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        try {
                            trySendBlocking(State.Success(snapshot.getValue(ResponseEntity::class.java)!!))
                        } catch (e: ClassCastException) {
                            Log.e(javaClass.name, e.localizedMessage!!)
                            trySendBlocking(State.Failed())
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e(javaClass.name, error.message)
                        trySendBlocking(State.Failed())
                    }
                }

            firebaseReference.addValueEventListener(valueEventListener)

            firebaseReference.get()

            awaitClose {
                firebaseReference.removeEventListener(valueEventListener)
            }
        }.flowOn(Dispatchers.IO)
}
