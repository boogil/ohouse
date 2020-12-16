package com.example.ohouse.domain.usecase

import com.example.ohouse.core.exception.Failure
import com.example.ohouse.data.data.PhotoDetail
import com.example.ohouse.domain.repository.OhouseRepository
import com.example.ohouse.core.functional.Either
import javax.inject.Inject

/**
 * 사진 상세 데이터 가져오기
 */
class GetPhotoDetail
@Inject constructor(private val ohouseRepository: OhouseRepository) : UseCase<PhotoDetail, GetPhotoDetail.Params>() {
    override suspend fun run(params: Params): Either<Failure, PhotoDetail> =
        ohouseRepository.network.getPhotoDetail(params.id)

    /**
     * @param page 페이징
     * @param per 아이템 갯수
     */
    data class Params(val id: Int)
}