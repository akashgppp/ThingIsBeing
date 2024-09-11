package com.skysoftsolution.thingisbeing.dashboard

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skysoftsolution.thingisbeing.R
import com.skysoftsolution.thingisbeing.dashboard.callbacks.AdapterClickListener
import com.skysoftsolution.thingisbeing.dashboard.entity.DashBoardModule

class CustomAdapterForDash(private val context: Context, private val dataList: DashBoardModule,
                           private val listenerAdapterClickListener: AdapterClickListener )
    : BaseAdapter() {

    private lateinit var dashBoardModules: DashBoardModule
    override fun getCount(): Int {
        return dataList.userList.size
    }

    override fun getItem(position: Int): Any {
        return dataList.userList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val viewHolder: ViewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                .inflate(R.layout.custom_layout_for_gridview, parent, false)
            viewHolder = ViewHolder()
            viewHolder.itemText = convertView.findViewById(R.id.textView3)
            viewHolder.icon = convertView.findViewById(R.id.bus)


            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }
        viewHolder.itemText.text = dataList.userList[position].Title
        viewHolder.icon.setImageDrawable(context.resources.getDrawable(dataList.userList[position].drawable))

        convertView?.setOnClickListener {
            listenerAdapterClickListener.onClickListener(dataList.userList[position])
        }


        return convertView!!
    }

    private class ViewHolder {
        lateinit var itemText: TextView
        lateinit var icon: ImageView;
    }

    fun setDashBoardModule(dashboardModule: DashBoardModule) {
        dashBoardModules = dashboardModule
        notifyDataSetChanged()
    }

}