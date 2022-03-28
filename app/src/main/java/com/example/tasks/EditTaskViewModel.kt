package com.example.tasks

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Dao
import kotlinx.coroutines.launch

class EditTaskViewModel(taskId:Long,val dao: TaskDao) : ViewModel() {
    val task = dao.get(taskId)
   private val _navigateToList= MutableLiveData<Boolean>(false)

    val navigateToList : LiveData<Boolean>
        get() {return _navigateToList}

    fun updateTask(){
        viewModelScope.launch {
            dao.insert(task.value!!)
            Log.d("anir","Task updated")
            _navigateToList.value=true
        }
    }

    fun deleteTask(){
        viewModelScope.launch {
            dao.delete(task.value!!)
            Log.d("anir","Task deleted")
            _navigateToList.value=true
        }
    }

    fun onNavigatedToList(){
        _navigateToList.value = false
    }
}