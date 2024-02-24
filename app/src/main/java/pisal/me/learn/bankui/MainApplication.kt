package pisal.me.learn.bankui

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import pisal.me.learn.bankui.di.appModule
import pisal.me.learn.bankui.di.dataModule
import pisal.me.learn.bankui.di.retrofitModule
import pisal.me.learn.bankui.di.viewModelModule

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(listOf(
                appModule, retrofitModule, dataModule, viewModelModule
            ))
        }
    }
}