package com.nzd.megatask

import android.app.Application
import androidx.room.Room
import com.nzd.megatask.database.AppDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App:Application() {

    override fun onCreate() {
        super.onCreate()

        val myModule = module {
            single { Room.databaseBuilder(this@App , AppDataBase::class.java , "mega").allowMainThreadQueries().build() }
        }

        startKoin {
            androidContext(this@App)
            modules(myModule)
        }
    }

}