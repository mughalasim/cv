package mughalasim.my.cv.di

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.FirebaseApp
import mughalasim.my.cv.R
import mughalasim.my.cv.services.IServiceNavigation
import mughalasim.my.cv.services.ServiceNavigation
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val applicationModule = module {

    single { FirebaseApp.initializeApp(androidApplication())}

    single <IServiceNavigation> { ServiceNavigation() }

    single <SharedPreferences> {
        androidApplication().getSharedPreferences (
            androidContext().getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
    }

    // TODO - Add firebase Analytics for tracking

}