package com.skysoftsolution.thingisbeing.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.skysoftsolution.thingisbeing.R
import com.skysoftsolution.thingisbeing.dailyRoutine.DailyRoutineActivity
import com.skysoftsolution.thingisbeing.dailyRoutine.model.DailyRoutineTask
import com.skysoftsolution.thingisbeing.dashboard.callbacks.AdapterClickListener
import com.skysoftsolution.thingisbeing.dashboard.entity.DashBoardModule
import com.skysoftsolution.thingisbeing.dashboard.entity.ModuleForUse
import com.skysoftsolution.thingisbeing.databinding.ActivityDashBoardBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DashBoardActivity : AppCompatActivity() , AdapterClickListener {
    private lateinit var binding: ActivityDashBoardBinding
    private lateinit var dashboardmodules: DashBoardModule
    private val viewModel: DashBoardViewModel = DashBoardViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide.with(this)
            .asGif()
            .load(R.drawable.dashboardgif)  // Your GIF file in res/drawable
            .into(binding.imageViewback)

        DateAndTimeWork()
        viewModel.userList.observe(this@DashBoardActivity, Observer { userList ->
            setAdapterData(userList)
        })




        val newUser1 = ModuleForUse(1, "Daily Routine ", R.drawable.daily_routine)
        viewModel.addModule(newUser1)
        val newUser = ModuleForUse(2, "Goals", R.drawable.goalsetting)
        viewModel.addModule(newUser)



    }
    private fun DateAndTimeWork() {
        binding.TimeCurrent.format12Hour = "hh:mm aa"
        val currentDate = System.currentTimeMillis()
        // Format date with "month day year" pattern
        val sdf = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
        val formattedDate = sdf.format(Date(currentDate))
        binding.monthCurrent.text = formattedDate
    }
    private fun setAdapterData(dashboardModule: DashBoardModule) {
        val adapter = CustomAdapterForDash(this@DashBoardActivity, dashboardModule,this
        )
      binding.gridLayout.adapter = adapter
    }

    // Handle item click here
    override fun onClickListener(module: ModuleForUse) {
       if(module.id==1){
           val intent = Intent(this, DailyRoutineActivity::class.java)
           intent.putExtra("moduleId", module.id)
           startActivity(intent)

       }else if(module.id==2){

       }

    }
}
