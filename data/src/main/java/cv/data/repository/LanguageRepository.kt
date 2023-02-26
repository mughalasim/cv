package cv.data.repository

import android.app.Application
import com.google.firebase.database.*
import cv.domain.State
import cv.domain.entities.LanguageEntity
import cv.domain.entities.getDefaultLanguage
import cv.domain.repositories.ILanguageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn

class LanguageRepository(application: Application, firebaseInstance: FirebaseDatabase): ILanguageRepository {

    private var dbReference: DatabaseReference = firebaseInstance.getReference("/language/" + application.resources.configuration.locales.get(0).language)

    override fun fetchDataFromFirebase() = callbackFlow<State<LanguageEntity>> {
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    trySendBlocking(State.Success(snapshot.getValue(LanguageEntity::class.java)!!))
                } catch (e: Exception) {
                    trySendBlocking(State.Success(getDefaultLanguage()))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                trySendBlocking(State.Success(getDefaultLanguage()))
            }
        }

        dbReference.addValueEventListener(valueEventListener)

        dbReference.get()

        awaitClose {
            dbReference.removeEventListener(valueEventListener)
        }
    }.flowOn(Dispatchers.IO)
}