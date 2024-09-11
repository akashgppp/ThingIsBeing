package com.skysoftsolution.thingisbeing.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skysoftsolution.thingisbeing.dailyRoutine.model.DailyRoutineViewModel
import com.skysoftsolution.thingisbeing.dashboard.DashBoardViewModel
import com.skysoftsolution.thingisbeing.repository.MainRepository


class MainViewModelFatcory() : ViewModelProvider.Factory {
   lateinit var repository: MainRepository
    lateinit var application: Application

    constructor(repository: MainRepository, application: Application) : this() {
        this.repository = repository
        this.application = application
    }

    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(DailyRoutineViewModel::class.java)) {
            return DailyRoutineViewModel(repository, application) as T
        }



        throw IllegalArgumentException("Unknown View Model Class")
    }
}