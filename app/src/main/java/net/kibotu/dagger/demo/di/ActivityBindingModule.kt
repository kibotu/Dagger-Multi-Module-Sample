package net.kibotu.dagger.demo.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.kibotu.dagger.demo.MainActivity
import net.kibotu.dagger.profiles.ProfileActivity
import net.kibotu.dagger.profiles.di.ProfileModule

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun profileActivity(): ProfileActivity
}