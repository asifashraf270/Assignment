package com.shisheo.demo.di.module

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.gurus.guruspos.di.module.ActivityModule
import com.gurus.guruspos.di.module.NetworkModule
import com.gurus.guruspos.di.module.PreferenceModule
import com.gurus.guruspos.di.module.ViewModelModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ActivityModule::class, NetworkModule::class, PreferenceModule::class, ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideResource(application: Application): Resources = application.resources


}