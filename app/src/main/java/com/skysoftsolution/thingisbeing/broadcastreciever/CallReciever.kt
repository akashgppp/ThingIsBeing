package com.skysoftsolution.thingisbeing.broadcastreciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.skysoftsolution.thingisbeing.R
import com.skysoftsolution.thingisbeing.datasource.DataAccessObj
import com.skysoftsolution.thingisbeing.datasource.DataBaseCreator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class CallReciever : BroadcastReceiver() {
    var tv_username: TextView? = null
    var tv_mobilenumber: TextView? = null
    var iv_for_Cross: ImageView? = null
    var flog = true
    lateinit var dataAccessObj: DataAccessObj


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context?, intent: Intent?) {
        dataAccessObj = context?.let { DataBaseCreator.getInstance(it).dataAccessObj }!!
        if (intent?.action.equals(TelephonyManager.ACTION_PHONE_STATE_CHANGED)) {
            val state = intent?.getStringExtra(TelephonyManager.EXTRA_STATE);
            if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
                var incomingNumber = intent?.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                if (incomingNumber != null) {
                    if (incomingNumber.startsWith("+91")) {
                        incomingNumber = incomingNumber.substring(3, 13);
                    }
                    if (incomingNumber != null && flog) {
                        flog = false

                    }

                }
            }
        }

    }

}

