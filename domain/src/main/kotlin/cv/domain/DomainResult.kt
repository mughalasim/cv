package cv.domain

sealed interface DomainResult<out D> {
    data class Success<out D>(val data: D) : DomainResult<D>

    data class Error<out D>(val error: DomainError) : DomainResult<D>
}
