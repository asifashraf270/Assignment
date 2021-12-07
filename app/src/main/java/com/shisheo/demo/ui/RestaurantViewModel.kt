package com.shisheo.demo.ui

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.shisheo.demo.responsentity.restaurantlist.RestaurantResponseModel
import com.shisheo.demo.utils.AbsentLiveData
import com.shisheo.demo.utils.Resource
import com.shisheo.demo.webservice.WebService
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Singleton

class RestaurantViewModel  @Inject constructor(val restaurantRepository: RestaurantRepository):ViewModel(){
    var loadRestaurantList=MutableLiveData<String>()
    private val compositeDisposable = CompositeDisposable()

    var restaurantListResponse:LiveData<Resource<RestaurantResponseModel>> = Transformations.switchMap(loadRestaurantList){
        if(it==null){
            return@switchMap AbsentLiveData.create<Resource<RestaurantResponseModel>>()
        }else{
            return@switchMap  restaurantRepository.getRestaurantList(compositeDisposable)
        }
    }
    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}