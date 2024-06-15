package br.com.mindbox.model.calendar

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tbl_calendar_event",
    indices = [Index(value = ["calendar_event_id"], unique = true)])
data class CalendarEvent(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "calendar_event_id")
    var calendarEventId: Long = 0,
    var title: String = "",
    var description: String = "",
    @ColumnInfo(name = "start_date") val startDate: Date? = null,
    @ColumnInfo(name = "end_date") val endDate: Date? = null,
    @ColumnInfo(name = "creator_id") val creatorId: Long
) {
    companion object {
        @JvmField
        val UNIQUE_INDEX_NAME = "unique_calendar_event_id"
    }
}