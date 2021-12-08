package com.assignment.demo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.assignment.demo.responsentity.MostViewedResponseModel
import com.assignment.demo.utils.AbsentLiveData
import com.assignment.demo.utils.Resource
import javax.inject.Inject

class HomeViewModel @Inject constructor(val homeRepository: HomeRepository) : ViewModel() {
    var loadList=MutableLiveData<String>()
    var section:String?=null
    var period:Int?=null
    var key:String?=null
    var mostViewedMost:LiveData<Resource<MostViewedResponseModel>> = Transformations.switchMap(loadList){
        if(it==null){
            return@switchMap AbsentLiveData.create<Resource<MostViewedResponseModel>>()
        }else{
        return@switchMap period?.toInt()?.let { it1 ->
            homeRepository.getMostViewArticle(section.toString(),
                it1,key.toString())
        }
        }
    }
}