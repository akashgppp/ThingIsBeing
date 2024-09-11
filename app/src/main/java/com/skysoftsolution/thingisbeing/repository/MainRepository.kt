package com.skysoftsolution.thingisbeing.repository
import com.skysoftsolution.thingisbeing.datasource.DataAccessObj
class MainRepository() {
    fun getDailyTaskDetails()= dataAccessObj?.getAllTaskDetails()

    var dataAccessObj: DataAccessObj? = null

    constructor(dataAccessObj: DataAccessObj) : this() {
        this.dataAccessObj = dataAccessObj
    }

}

