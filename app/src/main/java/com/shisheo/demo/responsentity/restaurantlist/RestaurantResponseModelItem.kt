package com.shisheo.demo.responsentity.restaurantlist

import com.google.gson.annotations.Expose

data class RestaurantResponseModelItem(
    val description: String?,
    val image_url: String?,
    val name: String?,
    val offer: String?,
    @Expose var ratingStar:Float=0f
)