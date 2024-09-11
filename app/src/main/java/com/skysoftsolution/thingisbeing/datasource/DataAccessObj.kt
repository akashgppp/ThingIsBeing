package com.skysoftsolution.thingisbeing.datasource
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.skysoftsolution.thingisbeing.dailyRoutine.model.DailyRoutineTask

@Dao
interface DataAccessObj {
    @Query("select * from tbl_daily_routine")
    fun getAllTaskDetails(): LiveData<List<DailyRoutineTask>?>
}