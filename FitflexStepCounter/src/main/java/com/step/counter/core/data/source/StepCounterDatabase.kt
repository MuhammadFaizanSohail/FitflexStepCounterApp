package com.step.counter.core.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.step.counter.core.data.source.util.Converters
import com.step.counter.core.domain.model.Day

@Database(entities = [Day::class], version = 1)
@TypeConverters(Converters::class)
abstract class StepCounterDatabase : RoomDatabase() {

    abstract val dayDao: DayDao

    companion object {
        const val DATABASE_NAME = "step_counter_database"
    }
}