package net.kibotu.dagger.demo

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerApplication
import net.kibotu.dagger.core.extensions.initStrictMode
import net.kibotu.dagger.demo.di.AppComponent
import net.kibotu.dagger.demo.di.DaggerAppComponent
import net.kibotu.resourceextension.resBoolean
import timber.log.Timber

class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            initStrictMode()
        }

        if (R.bool.enable_logging.resBoolean) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}