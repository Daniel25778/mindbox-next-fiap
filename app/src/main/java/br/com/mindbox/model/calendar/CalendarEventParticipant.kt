package br.com.mindbox.model.calendar

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import br.com.mindbox.model.user.User


@Entity(tableName= "tbl_calendar_event_participant",foreignKeys = [
    ForeignKey(entity = CalendarEvent::class,
        parentColumns = ["calendar_event_id"],
        childColumns = ["calendar_event_id"]),
    ForeignKey(entity = User::class,
        parentColumns = ["user_id"],
        childColumns = ["user_id"])
],primaryKeys = ["calendar_event_id", "user_id"])
data class CalendarEventParticipant(
    @ColumnInfo(name = "calendar_event_id")
    var calendarEventId: Long = 0,

    @ColumnInfo(name = "user_id")
    var userId: Long = 0
)