package net.kibotu.dagger.profiles.di

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import net.kibotu.dagger.core.ProfileInterface
import net.kibotu.dagger.profiles.MyProfileInterface
import net.kibotu.dagger.profiles.ProfileActivity
import javax.inject.Singleton

@Module
class ProfileModule {

    @Singleton
    @Provides
    fun provideProfileInterface(): ProfileInterface = MyProfileInterface()
}