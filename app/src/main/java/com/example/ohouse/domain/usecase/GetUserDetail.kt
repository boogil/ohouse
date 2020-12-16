package com.example.ohouse.domain.usecase

import com.example.ohouse.core.exception.Failure
import com.example.ohouse.data.data.User
import com.example.ohouse.domain.repository.OhouseRepository
import com.example.ohouse.core.functional.Either
import javax.inject.Inject

/**
 * 유저 상세 데이터 가져오기
 */
class GetUserDetail
@Inject constructor(private val ohouseRepository: OhouseRepository) : UseCase<User, GetUserDetail.Params>() {
    override suspend fun run(params: Params): Either<Failure, User> =
        ohouseRepository.network.getUserDetail(params.id)

    /**
     * @param page 페이징
     * @param per 아이템 갯수
     */
    data class Params(val id: Int)
}