package com.step.counter.features.settings.domain.usecase

import com.step.counter.core.domain.model.DaySettings
import com.step.counter.core.domain.repository.DayRepository


interface UpdateDaySettings {

    suspend operator fun invoke(daySettings: DaySettings)
}

class UpdateDaySettingsImpl(
    private val dayRepository: DayRepository
) : UpdateDaySettings {

    override suspend fun invoke(daySettings: DaySettings) {
        dayRepository.updateDaySettings(daySettings)
    }
}