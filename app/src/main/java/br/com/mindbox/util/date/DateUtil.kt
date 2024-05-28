package br.com.mindbox.util.date

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

class DateUtils {
    companion object {
        @JvmStatic
        @SuppressLint("SimpleDateFormat")
        fun getFormat(): SimpleDateFormat {
            return SimpleDateFormat("yyyy-MM-dd")
        }
    }
}