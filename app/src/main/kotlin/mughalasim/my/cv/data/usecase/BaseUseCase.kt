package mughalasim.my.cv.data.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseUseCaseInput<in Input, out Output> where Output : Any? {

    abstract suspend fun run(input: Input): BaseResult<Output, Exception>

    open operator fun invoke (
        scope: CoroutineScope,
        input: Input,
        onResult: (BaseResult<Output, Exception>) -> Unit = {}
    ) {
        scope.launch {
            val job = withContext(Dispatchers.IO) { run(input) }
            withContext(scope.coroutineContext) { onResult(job) }
        }
    }
}

abstract class BaseUseCase<out Output> where Output : Any? {

    abstract suspend fun run(): BaseResult<Output, Exception>

    open operator fun invoke (
        scope: CoroutineScope,
        onResult: (BaseResult<Output, Exception>) -> Unit = {}
    ) {
        scope.launch {
            val job = withContext(Dispatchers.IO) { run() }
            withContext(scope.coroutineContext) { onResult(job) }
        }
    }
}
