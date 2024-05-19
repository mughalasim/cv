package cv.data.repository

import android.util.Log
import cv.domain.repositories.AppLogLevel
import cv.domain.repositories.AppLoggerRepository

class AppLoggerRepositoryImp(val isEnabled: Boolean): AppLoggerRepository {
    override fun log(
        message: String,
        appLogLevel: AppLogLevel,
    ) {
        if (isEnabled){
            when(appLogLevel){
                AppLogLevel.DEBUG ->{
                    Log.d(javaClass.simpleName, message)
                }
                AppLogLevel.ERROR ->{
                    Log.e(javaClass.simpleName, message)
                }
            }
        }
    }
}
