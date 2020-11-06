package com.kks.techflittertestkirit.application

import android.app.Application
import android.content.Context

/**
 * Application Class
 *
 * @author Kirit Khnat
 * @since 05-11-2020
 */
class TechFlitterTestKirit : Application() {

    companion object {
        // Singleton Object
        private lateinit var singleton: TechFlitterTestKirit
        /*
            To get the Object for the Application Class
         */
        fun getInstance(): TechFlitterTestKirit = singleton

        /*
            To get context
         */
        fun getContext(): Context = singleton.applicationContext
    }

    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!
    override fun onCreate() {
        super.onCreate()
        // Required initialization logic here!
        singleton = this
    }

}