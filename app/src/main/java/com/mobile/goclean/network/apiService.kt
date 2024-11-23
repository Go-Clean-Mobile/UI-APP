@file:Suppress("ktlint:standard:filename")

package com.mobile.goclean.network

import android.util.Log
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

// Data class untuk deteksi
data class Detection(
    val bbox: List<Float>,
    val confidence: Float,
    val label: String,
)

data class ApiResponse(
    val detections: List<Detection>,
)

// Data class untuk login dan token
data class LoginRequest(
    val username: String,
    val password: String,
)

data class TokenResponse(
    val refresh: String,
    val access: String,
)

// User
data class User(
    val id: Int,
    val username: String,
    val first_name: String,
    val last_name: String,
    val email: String,
)

// Summary
data class Summary(
    val total: Int,
    val organic: Int,
    val inorganic: Int,
    val points: Int,
)

// History
data class History(
    val date: String,
    val points: Int,
    val category: String,
)

data class HistoryRequest(
    val points: Int,
    val category: String, // 1 untuk organik, 2 untuk anorganik
)

data class RegisterRequest(
    val username: String,
    val password: String,
    val email: String,
    val first_name: String,
    val last_name: String,
)

data class RegisterResponse(
    val message: String,
)

interface ApiService {
    // Waste Detection
    @Multipart
    @POST("api/detect/")
    suspend fun detectWaste(
        @Header("Authorization") token: String,
        @Part image: MultipartBody.Part,
    ): ApiResponse

    // Authentication
    @POST("api/user/login/")
    suspend fun login(
        @Body request: LoginRequest,
    ): Response<TokenResponse>

    @POST("api/user/register/")
    suspend fun registerUser(
        @Body request: RegisterRequest,
    ): Response<RegisterResponse>

    // User Profile
    @GET("api/user/profile/")
    suspend fun getUserProfile(
        @Header("Authorization") token: String,
    ): Response<User>

    // Report Summary
    @GET("api/user/report-summary/")
    suspend fun getReportSummary(
        @Header("Authorization") token: String,
    ): Response<Summary>

    // User History
    @GET("api/user/history/")
    suspend fun getUserHistory(
        @Header("Authorization") token: String,
    ): Response<List<History>>

    @POST("api/user/history/insert/")
    suspend fun insertUserHistory(
        @Header("Authorization") token: String,
        @Body history: HistoryRequest,
    ): Response<Unit>
}

object ApiClient {
    private var _accessToken: String? = null
    val accessToken: String?
        get() =
            synchronized(this) {
                _accessToken
            }

    fun initializeAccessToken(token: String) {
        synchronized(this) {
            if (_accessToken == null) {
                _accessToken = token
                Log.d("ApiClient", "Access token initialized: $token")
            } else {
                Log.d("ApiClient", "Access token is already initialized")
            }
        }
    }

    private val retrofit =
        Retrofit
            .Builder()
            .baseUrl("http://10.0.2.2:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
