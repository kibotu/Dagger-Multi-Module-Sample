package net.kibotu.dagger.demo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.kibotu.dagger.profiles.ProfileActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // start separate product feature.
        startActivity(Intent(this, ProfileActivity::class.java))
        finish()
    }
}