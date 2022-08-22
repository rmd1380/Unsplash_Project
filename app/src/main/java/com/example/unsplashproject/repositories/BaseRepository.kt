package com.example.unsplashproject.repositories

import androidx.constraintlayout.helper.widget.Flow
import com.example.unsplashproject.services.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {

    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {
        //work with live data
//        liveData(Dispatchers.IO) {
//            emit()
//        }
        return withContext(Dispatchers.IO)
        {
            try {
                val response: Response<T> = apiToBeCalled()
                if (response.isSuccessful) {
                    Resource.Success(data = response.body()!!)
                } else {
                    Resource.Error(message = response.errorBody().toString())
                }
            } catch (e: HttpException) {
                Resource.Error(message = e.message)
            } catch (e: IOException) {
                Resource.Error(message = "Check Your connection")
            } catch (e: Exception) {
                Resource.Error(message = "Something went wrong")
            }
        }
    }
}