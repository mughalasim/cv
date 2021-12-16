package mughalasim.my.cv

import android.app.Application
import mughalasim.my.cv.di.KoinContext
import mughalasim.my.cv.di.useCaseModule
import mughalasim.my.cv.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        KoinContext.koinApp = startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                listOf(
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }

}