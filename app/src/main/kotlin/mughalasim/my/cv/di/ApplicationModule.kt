package mughalasim.my.cv.di

import com.google.firebase.FirebaseApp
import mughalasim.my.cv.services.IServiceNavigation
import mughalasim.my.cv.services.ServiceNavigation
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val applicationModule = module {

    single { FirebaseApp.initializeApp(androidApplication())}

    single <IServiceNavigation>{ ServiceNavigation() }

}