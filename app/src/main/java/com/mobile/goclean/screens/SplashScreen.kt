package com.mobile.goclean.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobile.goclean.R
import com.mobile.goclean.theme.BackgroundGradient
import com.mobile.goclean.theme.OnPrimaryColor
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    var splashDuration = 1000L
    LaunchedEffect(key1 = true) {
        delay(splashDuration)
        navController.navigate("login_screen") {
            popUpTo("splash_screen") {
                inclusive = true
            }
        }
    }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(BackgroundGradient),
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
            style = MaterialTheme.typography.titleLarge,
            color = OnPrimaryColor,
        )
    }
}

@Preview
@Composable
private fun prev() {
    val navController = rememberNavController()
    SplashScreen(navController = navController)
}
