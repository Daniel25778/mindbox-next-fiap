package br.com.mindbox.util.convertion

import androidx.room.TypeConverter
import java.util.Date

class ConverterUtil {
    @TypeConverter
    fun fromListToString(participantsId: List<Long>): String {
        return participantsId.joinToString(",")
    }

    @TypeConverter
    fun fromStringToList(participantsId: String): List<Long> {
        return participantsId.split(",").map { it.toLong() }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }
}