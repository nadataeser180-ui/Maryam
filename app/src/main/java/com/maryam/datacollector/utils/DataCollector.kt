package com.maryam.datacollector.utils

import com.maryam.datacollector.data.DataEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DataCollector {

    suspend fun collectData(url: String): DataEntity? = withContext(Dispatchers.IO) {
        return@withContext try {
            val document = Jsoup.connect(url).get()
            val title = document.title()
            val description = document.selectFirst("meta[name=description]")?.attr("content")
                ?: document.selectFirst("p")?.text()
                ?: "لا يوجد وصف"

            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
            val currentDate = dateFormat.format(Date())

            DataEntity(
                url = url,
                title = title.ifEmpty { "بدون عنوان" },
                description = description.take(200),
                createdAt = currentDate
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
