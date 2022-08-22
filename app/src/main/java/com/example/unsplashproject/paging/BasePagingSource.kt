package com.example.unsplashproject.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.unsplashproject.model.response.FeedPhotoResponse
import com.example.unsplashproject.services.ServiceApi
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class BasePagingSource<T : Any>(private val backend: suspend (Int) -> Response<List<T>>) :
    PagingSource<Int, T>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val nextPageNumber = params.key ?: 1
        val response = backend.invoke(nextPageNumber)

        return if (response.isSuccessful) {
            val body = response.body()!!

            LoadResult.Page(
                data = body,
                prevKey = null,
                nextKey = nextPageNumber.plus(1)

            )
        } else {
            val errorBody = response.errorBody()
            LoadResult.Error(
                RuntimeException(errorBody.toString())
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}

//Not Generic

//class FeedPagingSource(var api: ServiceApi) : PagingSource<Int, FeedPhotoResponse>() {
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FeedPhotoResponse> {
//        val position = params.key ?: 1
//
//        return try {
//            val response = api.getPhoto(position, params.loadSize)
//            val body = response.body()
//            if (response.isSuccessful) {
//                LoadResult.Page(
//                    data = body ?: listOf(),
//                    prevKey = null,
//                    nextKey = if (body.isNullOrEmpty()) null else position + 1
//                )
//            } else {
//                return LoadResult.Error(java.lang.RuntimeException(response.message()))
//            }
//        } catch (e: IOException) {
//            LoadResult.Error(e)
//        } catch (e: HttpException) {
//            LoadResult.Error(e)
//        }
//
//
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, FeedPhotoResponse>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            val anchorPage = state.closestPageToPosition(anchorPosition)
//            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//        }
//    }
//}


