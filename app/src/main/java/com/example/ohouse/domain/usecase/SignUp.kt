package com.example.ohouse.domain.usecase

import com.example.ohouse.core.exception.Failure
import com.example.ohouse.domain.repository.OhouseRepository
import com.example.ohouse.core.functional.Either
import javax.inject.Inject

class SignUp
@Inject constructor(private val ohouseRepository: OhouseRepository) : UseCase<String, SignUp.Params>() {
    override suspend fun run(params: Params): Either<Failure, String> =
        ohouseRepository.network.signUp(params.nickName, params.introduction, params.pwd)

    data class Params(val nickName: String, val introduction: String, val pwd: String)
}