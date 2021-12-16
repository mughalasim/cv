package mughalasim.my.cv.data.usecase

sealed class BaseResult<out S, out F : Exception> {

    data class Success<out S>(val successType: S) : BaseResult<S, Nothing>()
    open class Failure(val exception: Exception) : BaseResult<Nothing, Exception>()

    fun result(
        onSuccess: (S) -> Unit = {},
        onFailure: (Exception) -> Unit = {}

    ): Unit = when (this) {
        is Success -> onSuccess(successType)
        is Failure -> onFailure(exception)
    }
}


