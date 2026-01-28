package com.step.counter.features.settings.domain.repository

import kotlinx.coroutines.flow.Flow
import com.step.counter.features.settings.domain.model.Settings

interface SettingsRepository {

    fun getSettings(): Flow<Settings>

    suspend fun updateDailyGoal(goal: Int)
}