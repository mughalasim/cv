package mughalasim.my.cv.ui.utils

import android.app.Application
import android.content.res.Resources

class AppContext(private val application: Application): IAppContext {

    override fun fetchResources(): Resources {
        return application.resources
    }

    override fun fetchAppContext(): Application {
       return application
    }
}

interface IAppContext{
    fun fetchResources(): Resources
    fun fetchAppContext(): Application
}