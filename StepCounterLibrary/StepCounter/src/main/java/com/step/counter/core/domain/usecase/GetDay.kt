package com.step.counter.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import com.step.counter.core.domain.model.Day
import java.time.LocalDate

interface GetDay {

    operator fun invoke(date: LocalDate): Flow<Day>
}