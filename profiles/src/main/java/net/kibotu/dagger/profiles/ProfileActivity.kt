package net.kibotu.dagger.profiles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector
import net.kibotu.dagger.core.WebViewInterface
import timber.log.Timber
import javax.inject.Inject

class ProfileActivity : AppCompatActivity(){

    @Inject
    lateinit var webViewInterface: WebViewInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_profile)

        AndroidInjection.inject(this)

        Timber.v("app implementation injected into library: webViewInterface $webViewInterface")
    }
}