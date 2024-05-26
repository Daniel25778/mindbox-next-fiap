package br.com.mindbox.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.mindbox.model.calendar.CalendarHoliday

@Dao
interface CalendarHolidayDAO {
    @Insert
    fun save(calendarHoliday: CalendarHoliday): Long

    @Update
    fun update(calendarHoliday: CalendarHoliday): Int

    @Delete
    fun delete(calendarHoliday: CalendarHoliday): Int

    @Query("SELECT * FROM tbl_calendar_holiday ORDER BY holiday_date DESC")
    fun findAll(): List<CalendarHoliday>
}