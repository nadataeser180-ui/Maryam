package com.maryam.datacollector

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.maryam.datacollector.clock.TimeZoneAdapter
import com.maryam.datacollector.clock.TimeZoneInfo
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.TimeZone

class ClockActivity : AppCompatActivity() {

    private lateinit var currentTimeView: TextView
    private lateinit var timeZoneListView: ListView
    private lateinit var backButton: Button
    private lateinit var adapter: TimeZoneAdapter
    private val handler = Handler(Looper.getMainLooper())
    private val timeZones = mutableListOf<TimeZoneInfo>()

    private val updateRunnable = object : Runnable {
        override fun run() {
            updateTime()
            handler.postDelayed(this, 1000) // Update every second
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clock)

        currentTimeView = findViewById(R.id.currentTimeView)
        timeZoneListView = findViewById(R.id.timeZoneListView)
        backButton = findViewById(R.id.backButton)

        // Initialize time zones
        setupTimeZones()

        // Setup adapter
        adapter = TimeZoneAdapter(this, timeZones)
        timeZoneListView.adapter = adapter

        // Back button
        backButton.setOnClickListener {
            finish()
        }

        // Start updating time
        handler.post(updateRunnable)
    }

    private fun setupTimeZones() {
        val zones = listOf(
            "Africa/Cairo" to "مصر (القاهرة)",
            "Asia/Riyadh" to "السعودية (الرياض)",
            "Asia/Dubai" to "الإمارات (دبي)",
            "Europe/London" to "بريطانيا (لندن)",
            "America/New_York" to "أمريكا (نيويورك)",
            "America/Los_Angeles" to "أمريكا (لوس أنجلس)",
            "Asia/Tokyo" to "اليابان (طوكيو)",
            "Australia/Sydney" to "أستراليا (سيدني)",
            "Europe/Paris" to "فرنسا (باريس)",
            "Asia/Singapore" to "سنغافورة"
        )

        for ((zoneId, name) in zones) {
            timeZones.add(TimeZoneInfo(zoneId, name, ""))
        }
    }

    private fun updateTime() {
        // Update current time
        val sdf = SimpleDateFormat("HH:mm:ss")
        val currentTime = sdf.format(Calendar.getInstance().time)
        currentTimeView.text = currentTime

        // Update time zones
        for (i in timeZones.indices) {
            val timeZone = TimeZone.getTimeZone(timeZones[i].zoneId)
            val cal = Calendar.getInstance(timeZone)
            val df = SimpleDateFormat("HH:mm:ss")
            df.timeZone = timeZone
            timeZones[i].time = df.format(cal.time)
        }
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(updateRunnable)
    }
}
