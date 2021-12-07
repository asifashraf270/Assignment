package com.shisheo.demo.ui

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.shisheo.demo.responsentity.restaurantlist.RestaurantResponseModel
import com.shisheo.demo.utils.Resource
import com.shisheo.demo.webservice.WebService
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RestaurantRepository @Inject constructor(val webService: WebService) {
    fun getRestaurantList(compositeDisposable: CompositeDisposable):MutableLiveData<Resource<RestaurantResponseModel>>{
        var result=MediatorLiveData<Resource<RestaurantResponseModel>>()
        result.value= Resource.loading()
        try {
            compositeDisposable.add(
                webService.getRestaurantList()
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            result.postValue(Resource.success(it,"Success"))
                        }, {
                            result.postValue(Resource.error(it.message))
                        })
            )
        } catch (e: Exception) {
            result.postValue(Resource.error(e.localizedMessage))
        }
        return result


    }
}