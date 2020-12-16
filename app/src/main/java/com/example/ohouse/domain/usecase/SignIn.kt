package com.example.ohouse.domain.usecase

import com.example.ohouse.core.exception.Failure
import com.example.ohouse.domain.repository.OhouseRepository
import com.example.ohouse.core.functional.Either
import javax.inject.Inject

class SignIn
@Inject constructor(private val ohouseRepository: OhouseRepository) : UseCase<String, SignIn.Params>() {
    override suspend fun run(params: Params): Either<Failure, String> =
        ohouseRepository.network.signIn(params.nickName, params.pwd)

    data class Params(val nickName: String, val pwd: String)
}