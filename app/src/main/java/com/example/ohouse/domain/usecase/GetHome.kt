package com.example.ohouse.domain.usecase

import com.example.ohouse.core.exception.Failure
import com.example.ohouse.data.data.Home
import com.example.ohouse.domain.repository.OhouseRepository
import com.example.ohouse.core.functional.Either
import javax.inject.Inject

/**
 * 홈 데이터 가져오기
 */
class GetHome
@Inject constructor(private val ohouseRepository: OhouseRepository) : UseCase<Home, UseCase.None>() {
    override suspend fun run(params: None): Either<Failure, Home> =
        ohouseRepository.network.getHome()
}