package com.mobile.goclean.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mobile.goclean.R
import com.mobile.goclean.theme.BackgroundGradient
import com.mobile.goclean.theme.OnPrimaryColor
import com.mobile.goclean.theme.ooredo
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val SplashDuration = 1000L
    LaunchedEffect(key1 = true) {
        delay(SplashDuration)
        navController.navigate("login_screen") {
            popUpTo("splash_screen") {
                inclusive = true
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().background(BackgroundGradient),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.leaf),
            contentDescription = "Login",
            tint = OnPrimaryColor,
            modifier = Modifier.size(150.dp),
        )
        Text(
            "GoClean",
            style =
                TextStyle(
                    fontFamily = ooredo,
                    color = OnPrimaryColor,
                ),
        )
    }
}
