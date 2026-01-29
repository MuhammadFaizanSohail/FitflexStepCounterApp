package com.step.counter.features.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.step.counter.StepCounter
import com.step.counter.core.data.repository.DayRepositoryImpl
import com.step.counter.core.domain.model.DaySettings
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.step.counter.features.settings.data.repository.SettingsRepositoryImpl
import com.step.counter.features.settings.domain.usecase.SettingsUseCases
import kotlinx.coroutines.launch
import java.time.LocalDate

class SettingsViewModel(
    private val settingsUseCases: SettingsUseCases
) : ViewModel() {

    private var observeSettingsChangesJob: Job? = null

    fun observeSettingsChanges() {
        observeSettingsChangesJob?.cancel()
        observeSettingsChangesJob = settingsUseCases.getSettings().onEach {
            settingsUseCases.updateDaySettings(
                DaySettings(
                    date = LocalDate.now(),
                    goal = it.dailyGoal,
                    height = it.height,
                    weight = it.weight,
                    stepLength = it.stepLength,
                    pace = it.pace
                )
            )
        }.launchIn(viewModelScope)
    }

    fun getSettings() = settingsUseCases.getSettings()

    fun updateDailyGoal(goal: Int) {
        viewModelScope.launch {
            settingsUseCases.updateDailyGoal(goal)
        }
    }

    companion object Factory : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            val application = checkNotNull(extras[APPLICATION_KEY])
            StepCounter.init(application)

            val settingsStore = StepCounter.settingsStore
            val settingsRepository = SettingsRepositoryImpl(settingsStore)
            val dayDatabase = StepCounter.stepCounterDatabase
            val dayRepository = DayRepositoryImpl(dayDatabase.dayDao)

            val settingsUseCases = SettingsUseCases(settingsRepository, dayRepository)

            return SettingsViewModel(settingsUseCases) as T
        }
    }
}