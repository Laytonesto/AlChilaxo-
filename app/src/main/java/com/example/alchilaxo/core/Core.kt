package com.example.alchilaxo.core

import android.app.Application
import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.multidex.MultiDex
import com.example.alchilaxo.core.di.AppComponent
import com.example.alchilaxo.core.di.DaggerAppComponent
import com.example.alchilaxo.core.util.Event


class Core : Application()  {

    companion object {
        lateinit var instance: Core private set
        lateinit var applicationComponent: AppComponent private set

        /** Live Data event bus */
        private val EVENT_BUS by lazy {
            MutableLiveData<Event<Any>>()
        }

        /** Application */
        val APP by lazy {
            instance
        }

        /** Application Context */
        val AppContext: Context by lazy {
            instance.applicationContext
        }

        /** Dependency Injection */
        val DI by lazy {
            applicationComponent
        }

        fun publish(event: Event<Any>) {
            EVENT_BUS.postValue(event)
        }
        fun listen(owner: LifecycleOwner, action: (Event<*>) -> Unit){
            EVENT_BUS.observe(owner, Observer {
                action.invoke(it)
            })
        }

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        applicationComponent = DaggerAppComponent
            .builder()
            .build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}