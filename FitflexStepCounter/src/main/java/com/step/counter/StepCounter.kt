package com.step.counter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.step.counter.core.data.source.StepCounterDatabase
import com.step.counter.features.settings.data.source.SettingsStore
import com.step.counter.features.settings.data.source.SettingsStoreImpl
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDate

object StepCounter {

    private var isInitialized = false

    lateinit var settingsStore: SettingsStore
        private set
    lateinit var stepCounterDatabase: StepCounterDatabase
        private set

    val currentDate = MutableStateFlow<LocalDate>(LocalDate.now())

    fun init(context: Context) {
        if (isInitialized) return
        
        val appContext = context.applicationContext

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(appContext)
        settingsStore = SettingsStoreImpl(sharedPreferences)

        stepCounterDatabase = Room.databaseBuilder(
            appContext,
            StepCounterDatabase::class.java,
            StepCounterDatabase.DATABASE_NAME
        ).build()

        registerMidnightTimer(appContext)
        
        isInitialized = true
    }

    private fun registerMidnightTimer(context: Context) {
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_TIME_TICK)
            addAction(Intent.ACTION_TIME_CHANGED)
            addAction(Intent.ACTION_TIMEZONE_CHANGED)
        }
        context.registerReceiver(midnightBroadcastReceiver, intentFilter)
    }

    private val midnightBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val today = LocalDate.now()
            if (today != currentDate.value) {
                currentDate.value = today
            }
        }
    }
}
