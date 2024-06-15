package br.com.mindbox.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.mindbox.model.calendar.CalendarEvent
import br.com.mindbox.model.calendar.CalendarEventWithUser
import java.util.Date

@Dao
interface CalendarEventDAO {
    @Insert
    fun save(calendarEvent: CalendarEvent): Long

    @Insert
    fun insertAll(calendarEvents: List<CalendarEvent>): List<Long>

    @Update
    fun update(calendarEvent: CalendarEvent): Int

    @Delete
    fun delete(calendarEvent: CalendarEvent): Int

    @Query("SELECT * FROM tbl_calendar_event WHERE calendar_event_id = :id")
    fun findById(id: Int): CalendarEventWithUser?

    @Query("SELECT * FROM tbl_calendar_event " +
            "INNER JOIN tbl_calendar_event_participant ON tbl_calendar_event.calendar_event_id = tbl_calendar_event_participant.calendar_event_id " +
            "WHERE tbl_calendar_event_participant.user_id = :participantId")
    fun findEventsByParticipantId(participantId: Long): List<CalendarEventWithUser>


    @Query("SELECT * FROM tbl_calendar_event " +
            "INNER JOIN tbl_calendar_event_participant ON tbl_calendar_event.calendar_event_id = tbl_calendar_event_participant.calendar_event_id " +
            "WHERE tbl_calendar_event_participant.user_id = :participantId " +
            "AND DATE(tbl_calendar_event.start_date) = DATE(:meetingDay)")
    fun findEventsByParticipantIdAndDay(participantId: Long, meetingDay: Date): List<CalendarEventWithUser>
}