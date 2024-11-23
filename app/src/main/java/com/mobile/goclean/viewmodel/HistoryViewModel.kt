package com.mobile.goclean.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.goclean.network.ApiClient
import com.mobile.goclean.network.History
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class HistoryUIState(
    val histories: List<History> = emptyList(),
    val error: String? = null,
)

class HistoryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HistoryUIState())
    val uiState: StateFlow<HistoryUIState> = _uiState

    fun fetchUserHistory() {
        viewModelScope.launch {
            _uiState.value =
                try {
                    val token =
                        ApiClient.accessToken?.let { "Bearer $it" }
                            ?: throw Exception("Token is null")

                    val response = ApiClient.apiService.getUserHistory(token)

                    if (response.isSuccessful) {
                        val histories = response.body()
                        Log.d("HistoryAPI", "Response: $histories")

                        if (!histories.isNullOrEmpty()) {
                            HistoryUIState(histories = histories)
                        } else {
                            Log.d("HistoryAPI", "Empty or null response")
                            HistoryUIState(error = "No history available")
                        }
                    } else {
                        val error = response.errorBody()?.string()
                        Log.e("HistoryAPI", "API error: $error")
                        HistoryUIState(error = "Failed to fetch history: $error")
                    }
                } catch (e: Exception) {
                    Log.e("HistoryAPI", "Exception: ${e.message}")
                    HistoryUIState(error = "Error: ${e.message}")
                }
        }
    }
}
