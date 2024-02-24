package pisal.me.learn.bankui.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher = IO,
    apiCall: suspend () -> T
): TResult<T> {
    return withContext(dispatcher) {
        try {
            TResult.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            TResult.Failure(throwable.message)
        }
    }
}