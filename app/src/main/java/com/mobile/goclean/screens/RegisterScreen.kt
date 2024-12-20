package com.mobile.goclean.screens

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobile.goclean.R
import com.mobile.goclean.theme.BackgroundGradient
import com.mobile.goclean.theme.OnPrimaryColor
import com.mobile.goclean.theme.PrimaryColor
import com.mobile.goclean.theme.ooredo
import com.mobile.goclean.theme.productsans
import com.mobile.goclean.viewmodel.RegisterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun registerScreen(
    navController: NavController,
    viewModel: RegisterViewModel = viewModel(),
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var isSubmitting by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

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
            registerHeader()
            Spacer(modifier = Modifier.height(20.dp))

            registerFields(
                username = username,
                onUsernameChange = { username = it },
                password = password,
                onPasswordChange = { password = it },
                email = email,
                onEmailChange = { email = it },
                firstName = firstName,
                onFirstNameChange = { firstName = it },
                lastName = lastName,
                onLastNameChange = { lastName = it },
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Tombol Register
            Box(
                modifier =
                    Modifier
                        .width(200.dp)
                        .background(BackgroundGradient, RoundedCornerShape(12.dp)),
            ) {
                Button(
                    onClick = {
                        isSubmitting = true
                        errorMessage = ""
                        viewModel.registerUser(
                            username = username,
                            password = password,
                            email = email,
                            firstName = firstName,
                            lastName = lastName,
                            onSuccess = {
                                isSubmitting = false
                                navController.navigate("login_screen") // Navigasi ke layar login
                            },
                            onError = { error ->
                                isSubmitting = false
                                errorMessage = error
                            },
                        )
                    },
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.White,
                        ),
                    modifier = Modifier.width(200.dp),
                    shape = RoundedCornerShape(8.dp),
                    enabled = !isSubmitting, // Nonaktifkan tombol saat proses
                ) {
                    if (isSubmitting) {
                        CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp))
                    } else {
                        Text(
                            "Register",
                            fontFamily = ooredo,
                            fontSize = 20.sp,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.login),
                            contentDescription = "Register",
                            tint = OnPrimaryColor,
                            modifier = Modifier.size(20.dp),
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Tampilkan error jika ada
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(vertical = 8.dp),
                )
            }

            loginLink(navController)
        }
    }
}

@Composable
fun registerHeader() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Register",
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
                style = TextStyle(fontSize = 25.sp, fontFamily = ooredo, color = OnPrimaryColor),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.leaf),
                contentDescription = "Register",
                tint = OnPrimaryColor,
                modifier = Modifier.size(30.dp),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun registerFields(
    username: String,
    onUsernameChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    firstName: String,
    onFirstNameChange: (String) -> Unit,
    lastName: String,
    onLastNameChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = username,
        onValueChange = onUsernameChange,
        label = { Text("Username") },
        placeholder = { Text("Masukkan Username Anda..") },
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
        value = firstName,
        onValueChange = onFirstNameChange,
        label = { Text("First Name") },
        placeholder = { Text("Masukkan First Name Anda..") },
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
        value = lastName,
        onValueChange = onLastNameChange,
        label = { Text("Last Name") },
        placeholder = { Text("Masukkan Last Name Anda..") },
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
fun registerButton(navController: NavController) {
    Box(
        modifier =
            Modifier
                .width(200.dp)
                .background(BackgroundGradient, RoundedCornerShape(12.dp)),
    ) {
        Button(
            onClick = { navController.navigate("login_screen") },
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White,
                ),
            modifier = Modifier.width(200.dp),
            shape = RoundedCornerShape(8.dp),
        ) {
            Text(
                "Register",
                fontFamily = ooredo,
                fontSize = 20.sp,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.login),
                contentDescription = "Register",
                tint = OnPrimaryColor,
                modifier = Modifier.size(20.dp),
            )
        }
    }
}

@Composable
fun loginLink(navController: NavController) {
    Row {
        Text(
            text = "Sudah ada akun? ",
            style = TextStyle(fontFamily = productsans),
        )
        ClickableText(
            text = AnnotatedString("Login"),
            onClick = { navController.navigate("login_screen") },
            style =
                TextStyle(
                    color = PrimaryColor,
                    fontFamily = productsans,
                    fontWeight = FontWeight.Bold,
                ),
        )
    }
}

@Preview
@Composable
private fun previewRegisterScreen() {
    val navController = rememberNavController()
    registerScreen(navController = navController)
}
