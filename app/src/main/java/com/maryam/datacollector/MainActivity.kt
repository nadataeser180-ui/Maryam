package com.maryam.datacollector

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.maryam.datacollector.data.DataEntity
import com.maryam.datacollector.database.AppDatabase
import com.maryam.datacollector.utils.DataCollector
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var urlInput: EditText
    private lateinit var collectButton: Button
    private lateinit var dataListView: ListView
    private lateinit var database: AppDatabase
    private lateinit var adapter: DataAdapter
    private val dataList = mutableListOf<DataEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize database
        database = AppDatabase.getInstance(this)

        // Initialize UI components
        urlInput = findViewById(R.id.urlInput)
        collectButton = findViewById(R.id.collectButton)
        dataListView = findViewById(R.id.dataListView)

        // Setup adapter
        adapter = DataAdapter(this, dataList)
        dataListView.adapter = adapter

        // Load existing data
        loadData()

        // Setup button click listener
        collectButton.setOnClickListener {
            val url = urlInput.text.toString().trim()
            if (url.isEmpty()) {
                Toast.makeText(this, "Please enter a URL or email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            collectButton.isEnabled = false
            collectButton.text = "جارٍ..."

            lifecycleScope.launch {
                try {
                    val data = DataCollector.collectData(url)
                    if (data != null) {
                        // Save to database
                        database.dataDao().insert(data)
                        dataList.add(0, data)
                        adapter.notifyDataSetChanged()
                        urlInput.text.clear()
                        Toast.makeText(
                            this@MainActivity,
                            "تم جمع البيانات بنجاح",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "فشل في جمع البيانات",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(
                        this@MainActivity,
                        "خطأ: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                } finally {
                    collectButton.isEnabled = true
                    collectButton.text = "جمع البيانات"
                }
            }
        }

        // Setup long click to delete
        dataListView.setOnItemLongClickListener { _, _, position, _ ->
            val item = dataList[position]
            lifecycleScope.launch {
                database.dataDao().delete(item)
                dataList.removeAt(position)
                adapter.notifyDataSetChanged()
                Toast.makeText(this@MainActivity, "تم الحذف", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    private fun loadData() {
        lifecycleScope.launch {
            val data = database.dataDao().getAllData()
            dataList.clear()
            dataList.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }
}
