package cv.data.retrofit

sealed interface ApiResult<out D> {
    class Success<out D>(val data: D) : ApiResult<D>

    class Error<out D>(val error: ApiError) : ApiResult<D>
}
