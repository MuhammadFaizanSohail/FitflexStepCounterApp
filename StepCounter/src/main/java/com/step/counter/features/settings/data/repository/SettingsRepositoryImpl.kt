package com.step.counter.features.settings.data.repository

import kotlinx.coroutines.flow.Flow
import com.step.counter.features.settings.data.source.SettingsStore
import com.step.counter.features.settings.domain.model.Settings
import com.step.counter.features.settings.domain.repository.SettingsRepository

class SettingsRepositoryImpl(
    private val settingsStore: SettingsStore
) : SettingsRepository {

    override fun getSettings(): Flow<Settings> {
        return settingsStore.getSettings()
    }
}