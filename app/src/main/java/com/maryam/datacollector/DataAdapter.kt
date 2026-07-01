package com.maryam.datacollector

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.maryam.datacollector.data.DataEntity

class DataAdapter(
    context: Context,
    private val dataList: List<DataEntity>
) : ArrayAdapter<DataEntity>(context, 0, dataList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.item_data,
            parent,
            false
        )

        val item = getItem(position) ?: return view

        val titleView: TextView = view.findViewById(R.id.itemTitle)
        val descriptionView: TextView = view.findViewById(R.id.itemDescription)
        val dateView: TextView = view.findViewById(R.id.itemDate)
        val urlView: TextView = view.findViewById(R.id.itemUrl)

        titleView.text = item.title ?: "بدون عنوان"
        descriptionView.text = item.description ?: "بدون وصف"
        dateView.text = item.createdAt
        urlView.text = item.url

        return view
    }
}
