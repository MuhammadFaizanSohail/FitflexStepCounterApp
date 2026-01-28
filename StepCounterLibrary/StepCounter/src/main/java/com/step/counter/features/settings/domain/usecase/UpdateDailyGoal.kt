package com.step.counter.features.settings.domain.usecase

import com.step.counter.features.settings.domain.repository.SettingsRepository

interface UpdateDailyGoal {

    suspend operator fun invoke(goal: Int)
}

class UpdateDailyGoalImpl(
    private val repository: SettingsRepository
) : UpdateDailyGoal {

    override suspend fun invoke(goal: Int) {
        repository.updateDailyGoal(goal)
    }
}
