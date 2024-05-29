package team.study.composemovieapp.features.common.mapper

import team.study.composemovieapp.features.common.entity.EntityWrapper
import team.study.composemovieapp.library.network.model.ApiResponse
import team.study.composemovieapp.library.network.model.ApiResult

abstract class BaseMapper<M, E> {

    fun mapFromResult(result: ApiResult<M>, extra: Any? = null): EntityWrapper<E> =
        when (result.response) {
            is ApiResponse.Success -> getSuccess(model = result.response.data, extra = extra)
            is ApiResponse.Fail -> getFailure(error = result.response.error)
        }

    abstract fun getSuccess(model: M?, extra: Any?): EntityWrapper<E>
    abstract fun getFailure(error: Throwable): EntityWrapper<E>

}