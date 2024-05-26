package br.com.mindbox.model.calendar

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.mindbox.model.user.User
import java.util.Date

@Entity(tableName = "tbl_calendar_event")
data class CalendarEvent(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "calendar_event_id")
    var title: String = "",
    var description: String = "",
    @ColumnInfo(name = "start_date") val startDate: Date? = null,
    @ColumnInfo(name = "end_date") val endDate: Date? = null,
    @ColumnInfo(name = "participants_id") val participantsId: List<Long> = emptyList(),
    @ColumnInfo(name = "creator_id") val creatorId: Long
)