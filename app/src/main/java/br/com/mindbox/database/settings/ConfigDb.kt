package br.com.mindbox.database.settings

import AppDatabaseCallback
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.mindbox.database.dao.CalendarEventDAO
import br.com.mindbox.database.dao.CalendarEventParticipantDAO
import br.com.mindbox.database.dao.CalendarHolidayDAO
import br.com.mindbox.database.dao.EmailCategoryDAO
import br.com.mindbox.database.dao.EmailDAO
import br.com.mindbox.database.dao.EmailRecipientDAO
import br.com.mindbox.database.dao.EmailTaskDAO
import br.com.mindbox.database.dao.UserDAO
import br.com.mindbox.model.calendar.CalendarEvent
import br.com.mindbox.model.calendar.CalendarEventParticipant
import br.com.mindbox.model.calendar.CalendarHoliday
import br.com.mindbox.model.email.Email
import br.com.mindbox.model.email.EmailApproval
import br.com.mindbox.model.email.EmailCategory
import br.com.mindbox.model.email.EmailRecipient
import br.com.mindbox.model.email.EmailTask
import br.com.mindbox.model.email.EmailTemplate
import br.com.mindbox.model.external_account.ExternalAccount
import br.com.mindbox.model.user.User
import br.com.mindbox.util.convertion.ConverterUtil

@Database(
    entities = [User::class, Email::class, EmailApproval::class, EmailCategory::class, EmailRecipient::class, EmailTask::class, EmailTemplate::class, ExternalAccount::class, CalendarHoliday::class, CalendarEvent::class, CalendarEventParticipant::class],
    version = 1
)
@TypeConverters(ConverterUtil::class)
abstract class ConfigDb : RoomDatabase() {

    abstract fun userDAO(): UserDAO
    abstract fun emailDAO(): EmailDAO
    abstract fun calendarHolidayDAO(): CalendarHolidayDAO
    abstract fun calendarEventDAO(): CalendarEventDAO
    abstract fun emailRecipientDAO(): EmailRecipientDAO
    abstract fun emailTaskDAO(): EmailTaskDAO
    abstract fun emailCategoryDAO(): EmailCategoryDAO
    abstract fun calendarEventParticipantDAO(): CalendarEventParticipantDAO

    companion object {

        @Volatile
        private var instance: ConfigDb? = null

        fun getDatabase(context: Context): ConfigDb {
            return instance ?: synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext, ConfigDb::class.java, "mindbox_db"
                ).addCallback(AppDatabaseCallback(context))
                    .allowMainThreadQueries() // Talvez seja necessário remover
                    .fallbackToDestructiveMigration().build()
                instance = newInstance
                newInstance
            }
        }
    }

}