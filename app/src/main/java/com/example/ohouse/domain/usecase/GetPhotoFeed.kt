package com.example.ohouse.domain.usecase

import com.example.ohouse.core.exception.Failure
import com.example.ohouse.data.data.Card
import com.example.ohouse.domain.repository.OhouseRepository
import com.example.ohouse.core.functional.Either
import javax.inject.Inject

/**
 * 사진피드 데이터 가져오기
 */
class GetPhotoFeed
@Inject constructor(private val ohouseRepository: OhouseRepository) : UseCase<ArrayList<Card>, GetPhotoFeed.Params>() {
    override suspend fun run(params: Params): Either<Failure, ArrayList<Card>> =
        ohouseRepository.network.getPhotoFeed(params.page, params.per)

    /**
     * @param page 페이징
     * @param per 아이템 갯수
     */
    data class Params(val page: Int, val per: Int)
}