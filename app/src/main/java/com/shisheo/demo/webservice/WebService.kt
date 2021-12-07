package com.shisheo.demo.webservice

import com.shisheo.demo.responsentity.restaurantlist.RestaurantResponseModel
import com.shisheo.demo.utils.WebApiConstants
import io.reactivex.Single
import retrofit2.http.GET

interface WebService {


    @GET(WebApiConstants.RESTAURANT_LIST)
    fun getRestaurantList():Single<RestaurantResponseModel>
}