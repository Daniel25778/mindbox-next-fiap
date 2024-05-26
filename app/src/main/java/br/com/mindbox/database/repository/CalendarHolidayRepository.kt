package br.com.mindbox.database.repository

import android.content.Context
import br.com.mindbox.database.dao.CalendarHolidayDAO
import br.com.mindbox.database.settings.ConfigDb
import br.com.mindbox.model.calendar.CalendarHoliday

class CalendarHolidayRepository(context: Context) {
    private val db: CalendarHolidayDAO = ConfigDb.getDatabase(context).calendarHolidayDAO()

    fun save(calendarHoliday: CalendarHoliday): Long {
        return db.save(calendarHoliday)
    }

    fun update(calendarHoliday: CalendarHoliday): Int {
        return db.update(calendarHoliday)
    }

    fun delete(calendarHoliday: CalendarHoliday): Int {
        return db.delete(calendarHoliday)
    }

    fun findAll(): List<CalendarHoliday> {
        return db.findAll()
    }
}