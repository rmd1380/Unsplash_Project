package com.example.unsplashproject.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.unsplashproject.model.sitesearchusermodel.Results

import com.example.unsplashproject.services.ServiceApi
import retrofit2.HttpException
import java.io.IOException
import java.lang.RuntimeException

class SearchUserPagingSource(var api: ServiceApi, val query: String) : PagingSource<Int, Results>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        val position = params.key ?: 1

        return try {
            val response = api.getUsersBySearch(query,position, params.loadSize)
            val body = response.body()
            if (response.isSuccessful) {
                LoadResult.Page(
                    data = body?.results?: listOf(),
                    prevKey = null,
                    nextKey = if (body?.results.isNullOrEmpty()) null else position + 1
                )
            } else {
                return LoadResult.Error(RuntimeException(response.message()))
            }
        } catch (ex: IOException) {
            LoadResult.Error(ex)
        } catch (ex: HttpException) {
            LoadResult.Error(ex)
        }


    }

    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}