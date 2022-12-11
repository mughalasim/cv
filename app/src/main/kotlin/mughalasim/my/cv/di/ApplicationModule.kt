package mughalasim.my.cv.di

import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val applicationModule = module {
    single{ FirebaseApp.initializeApp(androidApplication())}
}