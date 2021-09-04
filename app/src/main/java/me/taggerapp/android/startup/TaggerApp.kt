package me.taggerapp.android.startup

import android.app.Application

class TaggerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        FlipperBootstrapper.start(this)
    }
}