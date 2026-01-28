package com.step.counter.features.settings.data.source

import kotlinx.coroutines.flow.Flow
import com.step.counter.features.settings.domain.model.Settings

interface SettingsStore {

    fun getSettings(): Flow<Settings>

    suspend fun updateDailyGoal(goal: Int)
}