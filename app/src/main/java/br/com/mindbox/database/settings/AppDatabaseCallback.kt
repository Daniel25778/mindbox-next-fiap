import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.mindbox.database.mock.DefaultCalendarEventParticipants
import br.com.mindbox.database.mock.DefaultCalendarEvents
import br.com.mindbox.database.mock.DefaultCalendarHolidays
import br.com.mindbox.database.mock.DefaultEmailCategories
import br.com.mindbox.database.mock.DefaultRegisterUserDTOs
import br.com.mindbox.database.mock.DefaultSendEmailDTOs
import br.com.mindbox.database.settings.ConfigDb
import br.com.mindbox.service.AuthorizationService
import br.com.mindbox.service.EmailService
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AppDatabaseCallback(private val context: Context) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        populateDatabase()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun populateDatabase() {
        val database = ConfigDb.getDatabase(context)
        val authorizationService = AuthorizationService(context)
        val emailService = EmailService(context)

        GlobalScope.launch {
            database.emailCategoryDAO().insertAll(DefaultEmailCategories.get())
            val registerUserDTOs = DefaultRegisterUserDTOs.get()
            registerUserDTOs.forEach { registerUserDTO ->
                authorizationService.registerUser(registerUserDTO, mustAuthenticate = false)
            }

            val sendEmailDTOs = DefaultSendEmailDTOs.get()
            sendEmailDTOs.forEach { sendEmailDTO ->
                emailService.sendMail(sendEmailDTO)
            }

            database.calendarEventDAO().insertAll(DefaultCalendarEvents.get())
            database.calendarEventParticipantDAO().insertAll(DefaultCalendarEventParticipants.get())
            database.calendarHolidayDAO().insertAll(DefaultCalendarHolidays.get())
        }
    }
}
