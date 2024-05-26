package br.com.mindbox.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import br.com.mindbox.model.calendar.CalendarEvent
import br.com.mindbox.model.calendar.CalendarEventWithUser
import br.com.mindbox.model.calendar.CalendarHoliday
import br.com.mindbox.model.user.User
import java.util.Date

@Dao
interface CalendarEventDAO {
    @Insert
    fun save(calendarEvent: CalendarEvent): Long

    @Update
    fun update(calendarEvent: CalendarEvent): Int

    @Delete
    fun delete(calendarEvent: CalendarEvent): Int

    @Query("SELECT * FROM tbl_calendar_event WHERE calendar_event_id = :id")
    fun findById(id: Int): CalendarEventWithUser?

    @Query("SELECT * FROM tbl_calendar_event LEFT JOIN tbl_user AS participant ON ',' || tbl_calendar_event.participants_id || ',' LIKE '%,' || participant.user_id || ',%' WHERE participant.user_id = :participantId")
    fun findEventsByParticipantId(participantId: Long): List<CalendarEventWithUser>

    @Query("SELECT * FROM tbl_calendar_event LEFT JOIN tbl_user AS participant ON ',' || tbl_calendar_event.participants_id || ',' LIKE '%,' || participant.user_id || ',%' WHERE participant.user_id = :participantId AND (:meetingDay IS NULL OR date(start_date) = date(:meetingDay))")
    fun findEventsByParticipantIdAndDate(participantId: Long, meetingDay: Date?): List<CalendarEventWithUser>
}