package com.example.ohouse.core.exception

sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()

    object FileException : FeatureFailure()
    object RoomException : FeatureFailure()

    /**
     * API Response fail 에러
     * @param msg fail 에러 메시지
     */
    class ApiError(val msg: String? = null) : FeatureFailure()
}