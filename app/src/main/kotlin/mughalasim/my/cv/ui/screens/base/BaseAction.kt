package mughalasim.my.cv.ui.screens.base

interface BaseAction<State> {
    fun reduce(state: State): State
}
