package me.taggerapp.android.providers.networking

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.net.NetworkRequest
import android.os.Build

class NetworkUtils(private val context: Context) {

    private val connectivityManager: ConnectivityManager
        get() = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    //check https://developer.android.com/training/basics/network-ops/reading-network-state?hl=es-419
    //and https://developer.android.com/training/monitoring-device-state/connectivity-status-type?hl=es-419
    fun subscribeToNetworkState(isOneTime: Boolean = false, onResponse: (isConnected: Boolean) -> Unit) {
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                onResponse(true)
                unregisterIfApply()
            }

            override fun onLost(network: Network) {
                onResponse(false)
                unregisterIfApply()
            }

            private fun unregisterIfApply() {
                if (isOneTime) {
                    connectivityManager.unregisterNetworkCallback(this)
                }
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(callback)
        } else {
            val builder: NetworkRequest.Builder = NetworkRequest.Builder()
            connectivityManager.registerNetworkCallback(builder.build(), callback)
        }
    }

    @Deprecated("Use subscribeToNetworkState")
    fun isNetworkAvailable(): Boolean {
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnected ?: false
    }
}