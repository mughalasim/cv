package cv.data.retrofit

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class ApiResultCall<D>(
    private val callDelegate: Call<D>,
) : Call<ApiResult<D>> {
    @Suppress("detekt.MagicNumber")
    override fun enqueue(callback: Callback<ApiResult<D>>) =
        callDelegate.enqueue(
            object : Callback<D> {
                override fun onResponse(
                    call: Call<D>,
                    response: Response<D>,
                ) {
                    response.body()?.let {
                        when (response.code()) {
                            in 200..208 -> {
                                callback.onResponse(
                                    this@ApiResultCall,
                                    Response.success(ApiResult.Success(it))
                                )
                            }
                            in 400..409 -> {
                                callback.onResponse(
                                    this@ApiResultCall,
                                    Response.success(ApiResult.Error(ApiError.UNAUTHORISED)),
                                )
                            }
                        }
                    } ?: callback.onResponse(
                        this@ApiResultCall,
                        Response.success(ApiResult.Error(ApiError.SERVER))
                    )
                }

                override fun onFailure(
                    call: Call<D>,
                    throwable: Throwable,
                ) {
                    callback.onResponse(
                        this@ApiResultCall,
                        Response.success(ApiResult.Error(ApiError.UNKNOWN))
                    )
                    call.cancel()
                }
            },
        )

    override fun clone(): Call<ApiResult<D>> = ApiResultCall(callDelegate.clone())

    override fun execute(): Response<ApiResult<D>> {
        throw UnsupportedOperationException("ResponseCall does not support execute.")
    }

    override fun isExecuted(): Boolean = callDelegate.isExecuted

    override fun cancel() = callDelegate.cancel()

    override fun isCanceled(): Boolean = callDelegate.isCanceled

    override fun request(): Request = callDelegate.request()

    override fun timeout(): Timeout = callDelegate.timeout()
}
