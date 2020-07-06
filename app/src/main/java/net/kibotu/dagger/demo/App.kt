package net.kibotu.dagger.demo

import android.app.Application
import net.kibotu.dagger.core.extensions.initStrictMode
import net.kibotu.resourceextension.resBoolean
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            initStrictMode()
        }

        if (R.bool.enable_logging.resBoolean) {
            Timber.plant(Timber.DebugTree())
        }

        initDagger()
    }

    /**
     * Initializing dependency injection.
     */
    private fun initDagger() {


    }
}