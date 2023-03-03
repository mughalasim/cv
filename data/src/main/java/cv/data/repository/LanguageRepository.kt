package cv.data.repository

import android.app.Application
import android.util.Log
import com.google.firebase.database.*
import cv.domain.State
import cv.domain.entities.LanguageEntity
import cv.domain.repositories.ILanguageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn

class LanguageRepository(application: Application, firebaseInstance: FirebaseDatabase) :
    ILanguageRepository {

    private val locale = application.resources.configuration.locales.get(0).language
    private var dbReference: DatabaseReference = firebaseInstance.getReference("/language/$locale")

    override fun fetchLanguageFromFirebase() = callbackFlow<State<LanguageEntity>> {
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val responseHashMap = snapshot.value as HashMap<String, Any>

                    val singleTexts =
                        responseHashMap.filter { it.value !is ArrayList<*> } as HashMap<String, CharSequence>

                    val pluralTexts: HashMap<String, Array<CharSequence>> = hashMapOf()

                    responseHashMap.filter { it.value is ArrayList<*> }.forEach {
                        val values = it.value as ArrayList<CharSequence>
                        val chars = arrayOf<CharSequence>(values.toList().toString())
                        pluralTexts[it.key] = chars
                    }

                    trySendBlocking(State.Success(LanguageEntity(singleTexts, pluralTexts, locale)))

                } catch (e: Exception) {
                    Log.e(javaClass.name, e.localizedMessage!!)
                    trySendBlocking(State.Failed())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(javaClass.name, error.message)
                trySendBlocking(State.Failed())
            }
        }

        dbReference.addValueEventListener(valueEventListener)

        dbReference.get()

        awaitClose {
            dbReference.removeEventListener(valueEventListener)
        }
    }.flowOn(Dispatchers.IO)
}