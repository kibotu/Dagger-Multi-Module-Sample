package net.kibotu.dagger.core.glide

import android.content.Context
import android.util.Log
import androidx.annotation.Keep
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Priority
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import net.kibotu.dagger.core.R
import net.kibotu.resourceextension.resBoolean

/**
 * App Glide module configuration.
 */
@Keep
@GlideModule
class AppGlideModule : AppGlideModule() {

    private val runtimeMemoryChunkInBytes: Int
        get() = (Runtime.getRuntime().maxMemory() / 8).toInt()

    private val logLevel by lazy { if (R.bool.enable_glide_logging.resBoolean) Log.DEBUG else Log.ERROR }

    private val requestOptions by lazy {
        with(RequestOptions.noTransformation()) {
            priority(Priority.IMMEDIATE)
            dontAnimate()
            downsample(DownsampleStrategy.CENTER_INSIDE)
            skipMemoryCache(false)
            diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        }
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) = super.applyOptions(context, builder.apply {
        setDefaultRequestOptions(requestOptions)
        setMemoryCache(LruResourceCache(runtimeMemoryChunkInBytes.toLong()))
        setLogLevel(logLevel)
    })

    override fun isManifestParsingEnabled() = false
}