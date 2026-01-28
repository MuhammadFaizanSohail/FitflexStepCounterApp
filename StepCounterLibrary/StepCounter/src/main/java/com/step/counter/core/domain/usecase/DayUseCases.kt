package com.step.counter.core.domain.usecase

import com.step.counter.core.domain.repository.DayRepository
import com.step.counter.features.settings.domain.repository.SettingsRepository

class DayUseCases(
    dayRepository: DayRepository,
    settingsRepository: SettingsRepository
) {

    val getDay: GetDay = GetDayImpl(dayRepository, settingsRepository)
    val incrementStepCount: IncrementStepCount = IncrementStepCountImpl(dayRepository, getDay)
}