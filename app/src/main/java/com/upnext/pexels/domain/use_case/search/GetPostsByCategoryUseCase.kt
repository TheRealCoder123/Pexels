package com.upnext.pexels.domain.use_case.search

import android.util.Log
import com.upnext.pexels.common.Category
import com.upnext.pexels.common.Resource
import com.upnext.pexels.data.remote.Post
import com.upnext.pexels.data.repository.ISearchRepository
import com.upnext.pexels.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPostsByCategoryUseCase @Inject constructor (
    private val searchRepository: ISearchRepository
) {

    operator fun invoke(category: Category) : Flow<Resource<List<Post>>> = flow {
        emit(Resource.Loading())
        try {
            val result = searchRepository.getByCategory(category)
            Log.e("category posts", "$result")
            emit(Resource.Success(result))
        }catch (e: Exception){
            emit(Resource.Error(null, e.localizedMessage ?: "Unknown Error"))
        }
    }

}