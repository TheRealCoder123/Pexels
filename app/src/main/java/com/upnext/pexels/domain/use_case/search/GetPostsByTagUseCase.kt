package com.upnext.pexels.domain.use_case.search

import com.upnext.pexels.common.Resource
import com.upnext.pexels.data.remote.Post
import com.upnext.pexels.data.repository.ISearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPostsByTagUseCase @Inject constructor (
    private val searchRepository: ISearchRepository
)  {

    operator fun invoke(tag: String) : Flow<Resource<List<Post>>> = flow {
        emit(Resource.Loading())
        try {
            val result = searchRepository.getByTag(tag)
            emit(Resource.Success(result))
        }catch (e: Exception){
            emit(Resource.Error(null, e.localizedMessage ?: "Unknown Error"))
        }
    }

}