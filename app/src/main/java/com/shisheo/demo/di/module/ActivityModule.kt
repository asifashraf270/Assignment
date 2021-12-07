package com.gurus.guruspos.di.module

import com.shisheo.demo.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun mainactivity(): MainActivity
}