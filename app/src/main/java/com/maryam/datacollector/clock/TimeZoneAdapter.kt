package com.maryam.datacollector.clock

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.maryam.datacollector.R

class TimeZoneAdapter(
    context: Context,
    private val timeZones: List<TimeZoneInfo>
) : ArrayAdapter<TimeZoneInfo>(context, 0, timeZones) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.item_timezone,
            parent,
            false
        )

        val item = getItem(position) ?: return view

        val nameView: TextView = view.findViewById(R.id.timezoneName)
        val timeView: TextView = view.findViewById(R.id.timezoneTime)

        nameView.text = item.name
        timeView.text = item.time

        return view
    }
}
