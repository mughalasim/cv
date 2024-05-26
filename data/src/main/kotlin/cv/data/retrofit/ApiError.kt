package cv.data.retrofit

import cv.domain.DomainError

enum class ApiError {
    UNKNOWN,
    SERVER,
    NETWORK,
    UNAUTHORISED,
}

fun ApiError.toDomainError(): DomainError =
    when(this){
        ApiError.UNKNOWN -> DomainError.UNKNOWN
        ApiError.NETWORK -> DomainError.NETWORK
        ApiError.UNAUTHORISED -> DomainError.UNAUTHORISED
        else -> DomainError.SERVER
    }