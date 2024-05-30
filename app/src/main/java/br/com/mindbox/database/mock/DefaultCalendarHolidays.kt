package br.com.mindbox.database.mock

import br.com.mindbox.model.calendar.CalendarHoliday
import br.com.mindbox.util.date.DateUtils

class DefaultCalendarHolidays {
    companion object {
        fun get(): List<CalendarHoliday> {
            val format = DateUtils.getFormat()
            return listOf(
                CalendarHoliday(name = "Ano Novo", holidayDate = format.parse("2024-01-01")!!),
                CalendarHoliday(name = "Carnaval", holidayDate = format.parse("2024-02-25")!!),
                CalendarHoliday(name = "Páscoa", holidayDate = format.parse("2024-04-21")!!),
                CalendarHoliday(name = "Tiradentes", holidayDate = format.parse("2024-04-21")!!),
                CalendarHoliday(name = "Dia do Trabalho", holidayDate = format.parse("2024-05-01")!!),
                CalendarHoliday(name = "Independência do Brasil", holidayDate = format.parse("2024-09-07")!!),
                CalendarHoliday(name = "Nossa Senhora Aparecida", holidayDate = format.parse("2024-10-12")!!),
                CalendarHoliday(name = "Finados", holidayDate = format.parse("2024-11-02")!!),
                CalendarHoliday(name = "Proclamação da República", holidayDate = format.parse("2024-11-15")!!),
                CalendarHoliday(name = "Natal", holidayDate = format.parse("2024-12-25")!!)
            )
        }
    }
}
