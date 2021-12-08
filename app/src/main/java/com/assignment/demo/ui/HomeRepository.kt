package com.assignment.demo.ui


import androidx.lifecycle.MutableLiveData
import com.assignment.demo.responsentity.MostViewedResponseModel
import com.assignment.demo.utils.Resource
import com.assignment.demo.webservice.WebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(val webService: WebService) {

    fun getMostViewArticle(
        section: String,
        period: Int,
        key: String
    ): MutableLiveData<Resource<MostViewedResponseModel>> {
        var result = MutableLiveData<Resource<MostViewedResponseModel>>()
        result.value = Resource.loading()
        webService.getMostViewArticle(section, period, key)
            .enqueue(object : Callback<MostViewedResponseModel> {
                override fun onFailure(call: Call<MostViewedResponseModel>, t: Throwable) {
                    result.value = Resource.error(t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<MostViewedResponseModel>,
                    response: Response<MostViewedResponseModel>
                ) {
                    if (response.isSuccessful && response.code() == 200) {
                        result.value = Resource.success(response.body(), response.body()?.status)
                    } else {
                        result.value = Resource.error(response.body()?.status)
                    }
                }
            })
        return result
    }
}