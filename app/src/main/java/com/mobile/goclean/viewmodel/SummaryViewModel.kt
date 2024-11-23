package com.mobile.goclean.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.goclean.network.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ReportSummaryState(
    val total: Int = 0,
    val organic: Int = 0,
    val inorganic: Int = 0,
    val points: Int = 0,
    val error: String? = null,
)

class SummaryViewModel : ViewModel() {
    private val _state = MutableStateFlow(ReportSummaryState())
    val state: StateFlow<ReportSummaryState> = _state

    fun fetchReportSummary() {
        viewModelScope.launch {
            _state.value =
                try {
                    val token =
                        ApiClient.accessToken?.let { "Bearer $it" }
                            ?: throw Exception("Token is null")

                    val response = ApiClient.apiService.getReportSummary(token)

                    if (response.isSuccessful) {
                        response.body()?.let { summary ->
                            ReportSummaryState(
                                total = summary.total,
                                organic = summary.organic,
                                inorganic = summary.inorganic,
                                points = summary.points,
                            )
                        } ?: _state.value.copy(error = "Response body is null")
                    } else {
                        _state.value.copy(
                            error = "Failed: ${response.errorBody()?.string()}",
                        )
                    }
                } catch (e: Exception) {
                    _state.value.copy(error = "Error: ${e.message}")
                }
        }
    }
}
