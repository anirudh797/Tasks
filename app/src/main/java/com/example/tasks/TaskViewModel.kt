package com.example.tasks

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class TaskViewModel(val dao : TaskDao) : ViewModel() {

    var newTaskName =""
    val tasks = dao.getAll()
    val tasksString = Transformations.map(tasks){
        tasks-> formatTasks(tasks)
    }
    private val _navigateToTask = MutableLiveData<Long?>()
    val navigateToTask: LiveData<Long?>
    get() {
        return _navigateToTask
    }

    private fun formatTasks(tasks: List<Task>) : String {

        return tasks.fold(""){str,item -> str + '\n' + formatTask(item)}
    }

    private fun formatTask(task : Task) : String{
        var str = "ID: ${task.taskId}"
        str+='\n' + "Name : ${task.taskName}"
        str+='\n' + "Complete : ${task.taskDone}" + '\n'
        return str
    }

    fun addTask(){
        viewModelScope.launch {
            val task = Task()
            task.taskName = newTaskName
            dao.insert(task)
        }
    }

    fun onTaskClicked(taskId : Long){
        _navigateToTask.value = taskId
    }

    fun onTaskNavigated(){
        _navigateToTask.value = null
    }

}