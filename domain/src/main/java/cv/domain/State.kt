package cv.domain

open class State<T> {
    class Loading<T> : State<T>()

    data class Success<T>(val data: T) : State<T>()

    class Failed<T> : State<T>()
}

sealed class ConnectionState {
    data object Available : ConnectionState()

    data object Unavailable : ConnectionState()
}
