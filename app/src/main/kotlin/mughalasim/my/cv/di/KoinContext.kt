package mughalasim.my.cv.di

import org.koin.core.KoinApplication
import org.koin.core.module.Module

object KoinContext {
    var koinApp: KoinApplication? = null

    fun addModule(module: Module) {
        koinApp?.modules(module)
    }
}
