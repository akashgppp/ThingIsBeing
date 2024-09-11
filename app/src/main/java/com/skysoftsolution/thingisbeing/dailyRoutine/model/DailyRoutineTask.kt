package com.skysoftsolution.thingisbeing.dailyRoutine.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "tbl_daily_routine")
data class DailyRoutineTask(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val title: String,
    val content: String,
    val date: Long = System.currentTimeMillis() // Default to current timestamp
)