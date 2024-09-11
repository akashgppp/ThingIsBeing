package com.skysoftsolution.thingisbeing.dailyRoutine.model

import android.app.Application
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.skysoftsolution.thingisbeing.repository.MainRepository

class DailyRoutineViewModel(private val repository: MainRepository, application: Application) :
    AndroidViewModel(application), Observable {
    val progress = MutableLiveData<Boolean>()
    val success =  MutableLiveData<List<DailyRoutineTask>>()
    val error = MutableLiveData<String>()

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

    fun getDailyTaskDetails() = repository.getDailyTaskDetails()

    fun delete(note: DailyRoutineTask) {

    }
}