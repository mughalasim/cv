package cv.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import cv.domain.State
import cv.domain.entities.ResponseEntity
import cv.domain.repositories.IDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn

class DataRepository: IDataRepository {

    private val reference = FirebaseDatabase.getInstance().reference

    override fun fetchDataFromFirebase(): Flow<State<ResponseEntity>> = callbackFlow<State<ResponseEntity>> {

        val valueEventListener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot){
                println("DataRepository: Data received")
                trySendBlocking(State.Success(snapshot.getValue(ResponseEntity::class.java)!!))
            }
            override fun onCancelled(error: DatabaseError){
                println("DataRepository: Cancelled data received")
                trySendBlocking(State.Failed(error.message))
            }
        }

        reference.get().addOnFailureListener { error ->
            println("DataRepository: Internet issues")
            trySendBlocking(State.Failed(message = error.message ?: "Failed to fetch data from Server"))
        }

        reference.addValueEventListener(valueEventListener)

        awaitClose {
            reference.removeEventListener(valueEventListener)
        }
    }.flowOn(Dispatchers.IO)
}