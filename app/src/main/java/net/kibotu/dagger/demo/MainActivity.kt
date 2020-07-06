package net.kibotu.dagger.demo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import net.kibotu.dagger.core.ProfileInterface
import net.kibotu.dagger.profiles.ProfileActivity
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var profileInterface: ProfileInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        Timber.v("library implementation injected into app: profileInterface $profileInterface")

        // start separate product feature.
        startActivity(Intent(this, ProfileActivity::class.java))
        finish()
    }
}