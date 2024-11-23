package com.mobile.goclean.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.goclean.network.ApiClient
import com.mobile.goclean.network.User
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel : ViewModel() {
    var userFirstName = mutableStateOf<String?>(null)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    fun fetchUserProfile() {
        viewModelScope.launch {
            try {
                val token = ApiClient.accessToken
                if (token == null) {
                    errorMessage.value = "Token tidak tersedia. Silakan login kembali."
                    return@launch
                }

                val response: Response<User> = ApiClient.apiService.getUserProfile("Bearer $token")
                if (response.isSuccessful) {
                    val user = response.body()
                    userFirstName.value = user?.first_name // Update nilai userFirstName
                } else {
                    errorMessage.value = "Gagal memuat profil user: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                errorMessage.value = "Terjadi kesalahan: ${e.message}"
            }
        }
    }
}
