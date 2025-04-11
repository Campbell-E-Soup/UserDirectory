package com.example.lab_4.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.lab_4.UserApplication
import com.example.lab_4.data.User
import com.example.lab_4.data.UserRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface UserUiState {
    data class Success(val users: List<User>?) : UserUiState
    object Error : UserUiState
    object Loading : UserUiState
}

class UserViewModel(private val usersRepository: UserRepository) : ViewModel() { //view model
    var userUiState: UserUiState by mutableStateOf(UserUiState.Loading)
        private set
    //get users
    init {
        getUsers()
    }

    fun getUsers() { //users and error handling
        viewModelScope.launch {
            userUiState = UserUiState.Loading
            userUiState = try {
                UserUiState.Success(usersRepository.getUsers())
            } catch (e: IOException) {
                UserUiState.Error
            } catch (e: HttpException) {
                UserUiState.Error
            }
        }
    }

    companion object { //use user application
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as UserApplication)
                val userRepository = application.container.userRepository
                UserViewModel(userRepository)
            }
        }
    }
}