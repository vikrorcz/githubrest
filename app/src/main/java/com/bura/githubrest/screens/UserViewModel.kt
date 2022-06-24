package com.bura.githubrest.screens

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bura.githubrest.data.RestClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


class UserViewModel(username: String) : ViewModel() {

    //LiveData or StateFlow? investigate

    private val _login: MutableSharedFlow<String> = MutableStateFlow("")
    val login: Flow<String> = _login

    private val _name: MutableSharedFlow<String> = MutableStateFlow("")
    val name: Flow<String> = _name

    private val _id: MutableSharedFlow<Int> = MutableStateFlow(0)
    val id: Flow<Int> = _id

    private val _publicRepos: MutableSharedFlow<Int> = MutableStateFlow(0)
    val publicRepos: Flow<Int> = _publicRepos

    private val _avatarUrl: MutableSharedFlow<String> = MutableStateFlow("")
    val avatarUrl: Flow<String> = _avatarUrl

    init {
        viewModelScope.launch {
            val response = try {
                RestClient.api.getUser(username)
            } catch (e: IOException) {
                e.stackTrace
                return@launch
            } catch (e: HttpException) {
                e.stackTrace
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                _login.emit(response.body()!!.login)
                _name.emit(response.body()!!.name)
                _id.emit(response.body()!!.id)
                _publicRepos.emit(response.body()!!.public_repos)
                _avatarUrl.emit(response.body()!!.avatar_url)
            }
        }
    }
}