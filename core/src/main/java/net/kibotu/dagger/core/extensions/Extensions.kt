package net.kibotu.dagger.core.extensions


import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.StrictMode
import android.view.View
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract


/**
 * Returns `true` if this not null or empty.
 */
@OptIn(ExperimentalContracts::class)
inline fun <reified T : CharSequence> T?.isNotNullOrEmpty(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrEmpty != null)
    }

    return this != null && this.isNotEmpty()
}

/**
 * Launches [Intent.ACTION_VIEW] with provided url.
 *
 * @param urlString Valid [Uri]
 */
fun Context.launchUrl(urlString: String) = startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlString)))

/**
 * Returns `true` for dark tinted icons.
 */
var Activity.isLightStatusBar
    get() = window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR == window.decorView.systemUiVisibility
    set(enabled) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return

        if (enabled) {
            window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            window.decorView.systemUiVisibility = window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
    }

/**
 * Creates secure context for device protected storage.
 */
fun Context.safeContext(): Context = takeUnless {
    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> isDeviceProtectedStorage
        else -> true
    }
}?.run {
    applicationContext.let {
        ContextCompat.createDeviceProtectedStorageContext(it) ?: it
    }
} ?: this

/**
 * Setup for [StrictMode](https://developer.android.com/reference/android/os/StrictMode).
 */
fun Application.initStrictMode() {

    StrictMode.setThreadPolicy(
        StrictMode.ThreadPolicy.Builder()
            .detectCustomSlowCalls()
            .detectNetwork()
            .penaltyDeathOnNetwork()
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    detectResourceMismatches()
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    detectUnbufferedIo()
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    penaltyListener(Executor { }, (StrictMode.OnThreadViolationListener { it?.printStackTrace() }))
                }
            }
            .permitDiskReads()
            .detectDiskWrites()
            .penaltyLog()
//            .penaltyDeath()
            .penaltyDropBox()

            .build()
    )

    StrictMode.setVmPolicy(
        StrictMode.VmPolicy.Builder()
            .detectActivityLeaks()
            .detectLeakedClosableObjects()
            .detectLeakedSqlLiteObjects()
            .apply {

                detectFileUriExposure()

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    detectCleartextNetwork()
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    detectContentUriWithoutPermission()
                    // detectUntaggedSockets() // exoplayer
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    // detectNonSdkApiUsage() // exoplayer
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    detectCredentialProtectedWhileLocked()
                    detectImplicitDirectBoot()
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    penaltyListener(Executor { }, (StrictMode.OnVmViolationListener { it?.printStackTrace() }))
                }
            }
            .penaltyLog()
            .penaltyDropBox()
//            .penaltyDeath()
            .build()
    )
}