package com.mobile.goclean.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mobile.goclean.components.bottomBar
import com.mobile.goclean.components.topBar
import com.mobile.goclean.theme.BackgroundGradient
import com.mobile.goclean.theme.Green600
import com.mobile.goclean.theme.ooredo

@Composable
fun AddScreen(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = navBackStackEntry?.destination?.route ?: "add_screen"
    Scaffold(
        topBar = { topBar(navController = navController) },
        content = { paddingValues ->
            Column(
                modifier = Modifier.fillMaxSize().background(BackgroundGradient).padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    "[Tambah Data]",
                    style =
                        TextStyle(
                            fontSize = 50.sp,
                            fontFamily = ooredo,
                        ),
                )
            }
        },
        bottomBar = {
            Box(
                modifier =
                    Modifier
                        .background(Green600),
            ) {
                bottomBar(navController = navController, currentScreen = currentScreen)
            }
        },
        contentColor = Color.White,
    )
}
