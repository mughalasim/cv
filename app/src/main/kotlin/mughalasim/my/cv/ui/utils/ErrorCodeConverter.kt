package mughalasim.my.cv.ui.utils

import android.app.Application
import cv.domain.DomainError
import mughalasim.my.cv.R

interface ErrorCodeConverter{
    fun getMessage(domainError: DomainError): String
}

class ErrorCodeConverterImp (
    private val application: Application,
): ErrorCodeConverter {
    override fun getMessage(domainError: DomainError): String =
        when (domainError) {
            DomainError.UNKNOWN -> application.resources.getString(R.string.error_unknown)
            DomainError.SERVER -> application.resources.getString(R.string.error_server)
            DomainError.NETWORK -> application.resources.getString(R.string.error_internet_connection)
            DomainError.UNAUTHORISED -> application.resources.getString(R.string.error_unauthorised)
        }
}