package com.ferdsapp.core.ui.utils

import android.os.Build
import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.Locale
import java.util.TimeZone

object FormatTimeHelper {

    fun timeAgoFromIso(isoTime: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // SDK 26+
            val time = Instant.parse(isoTime)
            val now = Instant.now()

            val days = ChronoUnit.DAYS.between(time, now)
            val hours = ChronoUnit.HOURS.between(time, now)
            val minutes = ChronoUnit.MINUTES.between(time, now)

            when {
                days > 0 -> "$days hari lalu"
                hours > 0 -> "$hours jam lalu"
                minutes > 0 -> "$minutes menit lalu"
                else -> "baru saja"
            }
        } else {
            // SDK < 26
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }

            val timeMillis = runCatching { sdf.parse(isoTime)?.time }.getOrNull()
                ?: return "" // kalau gagal parse

            // DateUtils ngasih "5 minutes ago" sesuai locale device
            // Kalau mau full Indonesia custom, lihat opsi di bawah.
            DateUtils.getRelativeTimeSpanString(
                timeMillis,
                System.currentTimeMillis(),
                DateUtils.MINUTE_IN_MILLIS
            ).toString()
        }
    }

}