import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.mobile.goclean.network.ApiClient
import com.mobile.goclean.network.HistoryRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class DetectionViewModel : ViewModel() {
    suspend fun uploadImage(
        context: Context,
        uri: Uri,
    ): MultipartBody.Part =
        withContext(Dispatchers.IO) {
            val file = uriToFile(context, uri)
            val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("image", file.name, requestBody)
        }

    suspend fun getJsonResponse(part: MultipartBody.Part): String =
        withContext(Dispatchers.IO) {
            try {
                val token =
                    ApiClient.accessToken?.let { "Bearer $it" }
                        ?: return@withContext "Error: Token not found"

                val response = ApiClient.apiService.detectWaste(token, part)

                response.detections
                    .joinToString(separator = "\n") { detection ->
                        "Label: ${detection.label}, Confidence: ${detection.confidence}, BBox: ${detection.bbox}"
                    }.ifBlank { "No detections found" }
            } catch (e: Exception) {
                e.printStackTrace()
                "Error: ${e.message}"
            }
        }

    suspend fun insertHistoryToDatabase(
        points: Int,
        category: String,
    ) {
        try {
            val token =
                ApiClient.accessToken?.let { "Bearer $it" }
                    ?: throw Exception("Token is null")
            val request = HistoryRequest(points, category)
            val response = ApiClient.apiService.insertUserHistory(token, request)

            if (response.isSuccessful) {
                Log.d("History", "History inserted successfully")
            } else {
                Log.e("History", "Failed to insert history: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            Log.e("History", "Error: ${e.message}")
        }
    }

    private fun uriToFile(
        context: Context,
        uri: Uri,
    ): File {
        val inputStream =
            context.contentResolver.openInputStream(uri)
                ?: throw IllegalStateException("Cannot open input stream from URI")
        val tempFile = File(context.cacheDir, "temp_image.jpg")
        inputStream.use { input ->
            tempFile.outputStream().use { output -> input.copyTo(output) }
        }
        return tempFile
    }
}
