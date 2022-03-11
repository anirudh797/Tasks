import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Insert
    fun insert(task: Task)

    @Update
    fun update(task : Task)

    @Delete
    fun delete(task: Task)

    @Query("SELECT * From task_table where taskId = :taskId")
    fun get(taskId:Long) : LiveData<Task>

    @Query("SELECT * From task_table order by taskId Desc")
    fun getAll() : LiveData<List<Task>>
}