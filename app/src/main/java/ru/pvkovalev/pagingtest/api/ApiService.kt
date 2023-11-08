package ru.pvkovalev.pagingtest.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.pvkovalev.pagingtest.models.ResponseApi
import ru.pvkovalev.pagingtest.utils.Constants.END_POINT

interface ApiService {

    @GET(END_POINT)
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): Response<ResponseApi>

}