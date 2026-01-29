package com.step.counter.features.settings.domain.usecase

import kotlinx.coroutines.flow.Flow
import com.step.counter.features.settings.domain.model.Settings
import com.step.counter.features.settings.domain.repository.SettingsRepository

interface GetSettings {

    operator fun invoke(): Flow<Settings>
}

class GetSettingsImpl(
    private val repository: SettingsRepository
) : GetSettings {

    override fun invoke(): Flow<Settings> {
        return repository.getSettings()
    }
}