package com.skysoftsolution.thingisbeing.datasource

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.skysoftsolution.thingisbeing.dailyRoutine.model.DailyRoutineTask

@Database(
    entities = [DailyRoutineTask::class],
    version = 1,
    exportSchema = false
)
abstract class DataBaseCreator : RoomDatabase() {
    abstract val dataAccessObj: DataAccessObj

    companion object {
        private var INSTANCE: DataBaseCreator? = null
        fun getInstance(context: Context): DataBaseCreator {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DataBaseCreator::class.java,
                        "user_details_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
