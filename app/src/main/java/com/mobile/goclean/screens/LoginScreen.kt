package com.mobile.goclean.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobile.goclean.R
import com.mobile.goclean.network.ApiClient
import com.mobile.goclean.network.LoginRequest
import com.mobile.goclean.theme.BackgroundGradient
import com.mobile.goclean.theme.OnPrimaryColor
import com.mobile.goclean.theme.PrimaryColor
import com.mobile.goclean.theme.ooredo
import com.mobile.goclean.theme.productsans
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(BackgroundGradient),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Column(
            modifier =
                Modifier
                    .background(OnPrimaryColor, RoundedCornerShape(20.dp))
                    .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            header()
            Spacer(modifier = Modifier.height(20.dp))

            loginFields(
                email = email,
                onEmailChange = { email = it },
                password = password,
                onPasswordChange = { password = it },
            )

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            loginButton(
                isLoading = isLoading,
                onClick = {
                    isLoading = true
                    coroutineScope.launch {
                        handleLogin(
                            email = email,
                            password = password,
                            navController = navController,
                            onSuccess = { isLoading = false },
                            onError = {
                                isLoading = false
                                errorMessage = it
                            },
                        )
                    }
                },
            )

            Spacer(modifier = Modifier.height(12.dp))

            registerLink(navController)
        }
    }
}

@Composable
fun header() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Login",
            style = TextStyle(fontSize = 25.sp, fontFamily = ooredo),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Row(
            modifier =
                Modifier
                    .background(BackgroundGradient, RoundedCornerShape(12.dp))
                    .padding(8.dp, 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "GoClean",
                style =
                    TextStyle(
                        fontSize = 25.sp,
                        fontFamily = ooredo,
                        color = OnPrimaryColor,
                    ),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.leaf),
                contentDescription = "Leaf",
                tint = OnPrimaryColor,
                modifier = Modifier.size(30.dp),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginFields(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        label = { Text("Email") },
        placeholder = { Text("Masukkan Email Anda..") },
        colors =
            TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,
            ),
        shape = RoundedCornerShape(8.dp),
        textStyle = TextStyle(fontFamily = productsans, fontSize = 18.sp),
    )
    Spacer(modifier = Modifier.height(12.dp))
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text("Password") },
        placeholder = { Text("Masukkan Password Anda..") },
        visualTransformation = PasswordVisualTransformation(),
        colors =
            TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,
            ),
        shape = RoundedCornerShape(8.dp),
        textStyle = TextStyle(fontFamily = productsans, fontSize = 18.sp),
    )
}

@Composable
fun loginButton(
    isLoading: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier =
            Modifier
                .width(200.dp)
                .background(BackgroundGradient, RoundedCornerShape(12.dp)),
    ) {
        Button(
            onClick = onClick,
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White,
                ),
            modifier = Modifier.width(200.dp),
            shape = RoundedCornerShape(8.dp),
            enabled = !isLoading,
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = OnPrimaryColor,
                    modifier = Modifier.size(20.dp),
                )
            } else {
                Text(
                    "Login",
                    fontFamily = ooredo,
                    fontSize = 20.sp,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.login),
                    contentDescription = "Login",
                    tint = OnPrimaryColor,
                    modifier = Modifier.size(20.dp),
                )
            }
        }
    }
}

@Composable
fun registerLink(navController: NavController) {
    Row {
        Text(
            text = "Belum punya akun? ",
            style = TextStyle(fontFamily = productsans),
        )
        ClickableText(
            text = AnnotatedString("Daftar"),
            onClick = { navController.navigate("register_screen") },
            style =
                TextStyle(
                    color = PrimaryColor,
                    fontFamily = productsans,
                    fontWeight = FontWeight.Bold,
                ),
        )
    }
}

suspend fun handleLogin(
    email: String,
    password: String,
    navController: NavController,
    onSuccess: () -> Unit,
    onError: (String) -> Unit,
) {
    try {
        val loginRequest = LoginRequest(username = email, password = password)
        val response = ApiClient.apiService.login(loginRequest)

        if (response.isSuccessful) {
            response.body()?.access?.let { token ->
                ApiClient.initializeAccessToken(token)
                Log.d("AuthToken", "Token successfully saved: Bearer $token")
                navController.navigate("home_screen")
                onSuccess()
            } ?: run {
                Log.e("AuthToken", "Token is null in response")
                onError("Token is null in response")
            }
        } else {
            val error = response.errorBody()?.string()
            Log.e("LoginError", "Login failed: $error")
            onError("Login gagal: Username atau password salah")
        }
    } catch (e: Exception) {
        Log.e("LoginError", "Exception during login: ${e.message}")
        onError("Terjadi kesalahan: ${e.message}")
    }
}

@Preview
@Composable
private fun previewLoginScreen() {
    val navController = rememberNavController()
    loginScreen(navController = navController)
}
