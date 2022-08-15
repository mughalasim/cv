package mughalasim.my.cv

import android.app.Application
import mughalasim.my.cv.di.repositoryModule
import mughalasim.my.cv.di.useCaseModule
import mughalasim.my.cv.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }

}