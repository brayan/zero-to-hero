package br.com.sailtech.zerotohero.mobile

import android.app.Application
import br.com.sailtech.zerotohero.mobile.feature.habits.di.commonHabitsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(commonHabitsModule)
        }
    }
}
