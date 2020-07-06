package net.kibotu.dagger.demo.di

import dagger.Module
import dagger.Provides
import net.kibotu.dagger.core.ProfileInterface
import net.kibotu.dagger.core.WebViewInterface
import net.kibotu.dagger.demo.MyWebViewInterface
import net.kibotu.dagger.profiles.MyProfileInterface
import javax.inject.Singleton


@Module
class CoreModule {

    @Singleton
    @Provides
    fun provideWebViewInterface() : WebViewInterface = MyWebViewInterface()
}