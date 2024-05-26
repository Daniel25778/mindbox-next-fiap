package br.com.mindbox.database.repository

import android.content.Context
import br.com.mindbox.database.dao.CalendarEventDAO
import br.com.mindbox.database.settings.ConfigDb
import br.com.mindbox.model.calendar.CalendarEvent
import br.com.mindbox.model.calendar.CalendarEventWithUser
import java.util.Date

class CalendarEventRepository(context: Context) {
    private val db: CalendarEventDAO = ConfigDb.getDatabase(context).calendarEventDAO()

    fun save(calendarEvent: CalendarEvent): Long {
        return db.save(calendarEvent)
    }

    fun update(calendarEvent: CalendarEvent): Int {
        return db.update(calendarEvent)
    }

    fun delete(calendarEvent: CalendarEvent): Int {
        return db.delete(calendarEvent)
    }

    fun findEventsByParticipantId(participantId: Long): List<CalendarEventWithUser> {
        return db.findEventsByParticipantId(participantId)
    }

    fun findEventsByParticipantIdAndDate(participantId: Long, meetingDay: Date): List<CalendarEventWithUser> {
        return db.findEventsByParticipantIdAndDate(participantId, meetingDay)
    }
}