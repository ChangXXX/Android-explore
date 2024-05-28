package team.study.composemovieapp.library.network.retrofit

import java.lang.reflect.Type
import team.study.composemovieapp.library.network.model.NetworkRequestInfo
import team.study.composemovieapp.library.network.model.ApiResult

interface NetworkRequestFactory {

    suspend fun <T> create(
        url: String,
        requestInfo: NetworkRequestInfo = NetworkRequestInfo.Builder().build(),
        type: Type
    ): ApiResult<T>
}
