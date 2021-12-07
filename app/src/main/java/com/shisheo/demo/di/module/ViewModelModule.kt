package com.gurus.guruspos.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gurus.guruspos.di.base.ViewModelFactory
import com.shisheo.demo.ui.RestaurantViewModel

import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RestaurantViewModel::class)
    abstract fun bindRestaurantViewModel(demoViewModel: RestaurantViewModel): ViewModel
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Retention(AnnotationRetention.RUNTIME)
    @MapKey
    annotation class ViewModelKey(val value: KClass<out ViewModel>)

}
