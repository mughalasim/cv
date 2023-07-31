package mughalasim.my.cv

import android.app.Application
import android.content.res.Resources
import dev.b3nedikt.app_locale.AppLocale
import dev.b3nedikt.restring.Restring
import dev.b3nedikt.reword.RewordInterceptor
import dev.b3nedikt.viewpump.ViewPump
import mughalasim.my.cv.di.applicationModule
import mughalasim.my.cv.di.repositoryModule
import mughalasim.my.cv.di.useCaseModule
import mughalasim.my.cv.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        Restring.init(this)
        ViewPump.init(RewordInterceptor)
        Restring.locale = this.resources.configuration.locales.get(0)

        startKoin {
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            modules(
                listOf(
                    applicationModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }

    override fun getResources(): Resources {
        return AppLocale.wrapResources(applicationContext, super.getResources())
    }

}