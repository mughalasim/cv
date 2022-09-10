package mughalasim.my.cv.di

import com.google.firebase.FirebaseApp
import mughalasim.my.cv.ui.utils.AppContext
import mughalasim.my.cv.ui.utils.IAppContext
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val applicationModule = module {
    single<IAppContext> { AppContext(androidApplication()) }
    single{ FirebaseApp.initializeApp(androidApplication())}
}