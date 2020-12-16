package com.example.ohouse.domain.repository.network

import com.example.ohouse.core.exception.Failure
import com.example.ohouse.data.domain.BaseEntity
import com.example.ohouse.core.functional.Either
import io.reactivex.Single
import retrofit2.Call

open class NetworkTypeCall {
    @Suppress("CAST_NEVER_SUCCEEDS")
    fun <T, R> request(
        call: Call<T>,
        transform: (T) -> R,
        default: R
    ): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> {
                    var either: Either<Failure, R>
                    try {
                        (response.body() as BaseEntity).apply {
                            if (ok) {
                                either = Either.Right(response.body()?.let { transform(it) }
                                    ?: run { default })
                            } else {
                                either = Either.Left(Failure.ApiError(errorMsg))
                            }
                        }
                    } catch (e: ClassCastException) {
                        either = Either.Left(Failure.ServerError)
                    }
                    return either
                }
                false -> Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }
}

interface NetworkTypeSingle {
    fun <T, R> request(single: Single<T>, transform: (T) -> R, default: R): Either<Failure, R>
}