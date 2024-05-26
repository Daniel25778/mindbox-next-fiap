package br.com.mindbox.model.calendar

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tbl_calendar_holiday")
data class CalendarHoliday(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "calendar_holiday_id")
    var id: Long = 0,
    var name: String = "",
    @ColumnInfo(name = "holiday_date") var holidayDate: Date
)