package me.taggerapp.android.startup

import android.content.Context
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import me.taggerapp.android.BuildConfig

object FlipperBootstrapper {

    private lateinit var networkPlugin: NetworkFlipperPlugin

    fun start(context: Context) {
        SoLoader.init(context, false)
        if (!BuildConfig.DEBUG || !FlipperUtils.shouldEnableFlipper(context)) {
            return
        }

        networkPlugin = buildNetworkingPlugin()

        AndroidFlipperClient.getInstance(context).apply {
            addPlugin(buildInspectorPlugin(context))
            addPlugin(networkPlugin)
            start()
        }
    }

    fun buildOkhttpInterceptor(): FlipperOkhttpInterceptor? {
        if (::networkPlugin.isInitialized.not()) return null
        return FlipperOkhttpInterceptor(networkPlugin)
    }

    private fun buildInspectorPlugin(context: Context): InspectorFlipperPlugin {
        return InspectorFlipperPlugin(context, DescriptorMapping.withDefaults())
    }

    private fun buildNetworkingPlugin(): NetworkFlipperPlugin {
        return NetworkFlipperPlugin()
    }
}