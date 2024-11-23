import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.goclean.network.ApiClient
import com.mobile.goclean.network.RegisterRequest
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    fun registerUser(
        username: String,
        password: String,
        email: String,
        firstName: String,
        lastName: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
    ) {
        viewModelScope.launch {
            try {
                val request =
                    RegisterRequest(
                        username = username,
                        password = password,
                        email = email,
                        first_name = firstName,
                        last_name = lastName,
                    )
                val response = ApiClient.apiService.registerUser(request)
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                    onError(errorMessage)
                }
            } catch (e: Exception) {
                onError(e.message ?: "An unexpected error occurred")
            }
        }
    }
}
