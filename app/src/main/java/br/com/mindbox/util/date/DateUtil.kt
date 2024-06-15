package br.com.mindbox.util.date

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.abs

class DateUtils {
    companion object {
        @JvmStatic
        @SuppressLint("SimpleDateFormat")
        fun getDateFormat(): SimpleDateFormat {
            return SimpleDateFormat("yyyy-MM-dd")
        }

        @JvmStatic
        @SuppressLint("SimpleDateFormat")
        fun getDateTimeFormat(): SimpleDateFormat {
            return SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        }

        @JvmStatic
        @SuppressLint("SimpleDateFormat")
        fun getBrazilianDateTimeFormat(): SimpleDateFormat {
            return SimpleDateFormat("dd/MM/yyyy, 'às' HH:mm")
        }

        @JvmStatic
        fun calculateDurationDescription(startDate: Date, endDate: Date): String {
            val differenceInMillis = abs(endDate.time - startDate.time)
            val hours = differenceInMillis / (1000 * 60 * 60)
            val minutes = (differenceInMillis / (1000 * 60)) % 60

            return when {
                hours > 1 -> "$hours horas e $minutes minutos"
                hours == 1L -> {
                    if (minutes == 0L) {
                        "1 hora"
                    } else {
                        "1 hora e $minutes minutos"
                    }
                }
                minutes > 30 -> "Mais de meia hora"
                minutes == 30L -> "Meia hora"
                minutes > 1 -> "$minutes minutos"
                else -> "Menos de um minuto"
            }
        }

        @JvmStatic
        @SuppressLint("SimpleDateFormat")
        fun formatRelativeDate(date: Date): String {
            val now = Date()
            val differenceInMillis = now.time - date.time
            val oneDayInMillis = 1000L * 60 * 60 * 24
            val oneYearInMillis = 1000L * 60 * 60 * 24 * 365

            val dateFormatter = SimpleDateFormat("d 'de' MMM", Locale("pt", "BR"))
            val timeFormatter = SimpleDateFormat("HH:mm")
            val yearFormatter = SimpleDateFormat("d 'de' MMM 'de' yyyy", Locale("pt", "BR"))

            return when {
                differenceInMillis < oneDayInMillis -> {
                    timeFormatter.format(date)
                }
                differenceInMillis < oneYearInMillis -> {
                    dateFormatter.format(date)
                }
                else -> {
                    yearFormatter.format(date)
                }
            }
        }
    }
}