package com.assignment.demo.webservice

import com.assignment.demo.responsentity.MostViewedResponseModel
import com.assignment.demo.utils.WebApiConstants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface WebService {

    @GET(WebApiConstants.MOST_VIEWED + "{section}/{period}.json")
    fun getMostViewArticle(
        @Path("section") section: String,
        @Path("period") period: Int,
        @Query("api-key") apiKey: String
    ): Call<MostViewedResponseModel>

}