import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version =1 , exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    // tells room that we want to use the data access methods
    //in taskDao
    abstract val taskDao: TaskDao

    companion object{

        private var INSTANCE : TaskDatabase? = null

        fun getInstance(context: Context) : TaskDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDatabase::class.java,
                        "tasks_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
                }
            }

        }
    }

