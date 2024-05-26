import android.annotation.SuppressLint
import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.mindbox.R
import br.com.mindbox.database.settings.ConfigDb
import br.com.mindbox.model.email.EmailCategory
import br.com.mindbox.model.email.EmailCategoryName
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

        GlobalScope.launch {
            database.emailCategoryDAO().insertAll(getDefaultCategories())
        }
    }

    companion object {
        @SuppressLint("PrivateResource")
        private fun getDefaultCategories(): List<EmailCategory> {
            return listOf(
                EmailCategory(name = EmailCategoryName.EDUCATION, iconResId = androidx.core.R.drawable.ic_call_decline_low),
            )
        }
    }
}
