package com.gurus.guruspos.di.module

import com.assignment.demo.ui.HomeScreen
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {


    @ContributesAndroidInjector
    abstract fun homeActivity():HomeScreen

}