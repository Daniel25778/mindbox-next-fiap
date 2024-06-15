package br.com.mindbox.database.repository

import android.content.Context
import br.com.mindbox.database.dao.CalendarEventParticipantDAO
import br.com.mindbox.database.settings.ConfigDb
import br.com.mindbox.model.calendar.CalendarEventParticipant

class CalendarEventParticipantRepository(context: Context) {
    private val db: CalendarEventParticipantDAO = ConfigDb.getDatabase(context).calendarEventParticipantDAO()

    fun insertAll(calendarEventParticipants: List<CalendarEventParticipant>): List<Long> {
        return db.insertAll(calendarEventParticipants)
    }
}