package cv.data.retrofit

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class ApiCallAdapter<D>(
    private val successType: Type,
) : CallAdapter<D, Call<ApiResult<D>>> {
    override fun responseType(): Type = successType

    override fun adapt(call: Call<D>): Call<ApiResult<D>> = ApiResultCall(call)
}
