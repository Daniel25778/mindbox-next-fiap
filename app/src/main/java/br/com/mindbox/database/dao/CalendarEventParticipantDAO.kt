package br.com.mindbox.database.dao

import androidx.room.Dao
import androidx.room.Insert
import br.com.mindbox.model.calendar.CalendarEventParticipant

@Dao
interface CalendarEventParticipantDAO {
    @Insert
    fun save(calendarEventParticipant: CalendarEventParticipant): Long

    @Insert
    fun insertAll(calendarEventParticipants: List<CalendarEventParticipant>): List<Long>
}