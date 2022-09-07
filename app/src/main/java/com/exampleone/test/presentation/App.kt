package com.exampleone.test.presentation

import android.app.Application
import com.exampleone.test.di.card
import com.exampleone.test.di.coffee
import com.exampleone.test.di.order
import com.exampleone.test.di.orderApi
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App:Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Koin Android logger
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            //inject Android context
            androidContext(this@App)

            modules(coffee, card, order, orderApi)

        }

    }


}