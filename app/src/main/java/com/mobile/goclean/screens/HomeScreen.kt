package com.mobile.goclean.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mobile.goclean.components.bottomBar
import com.mobile.goclean.components.home.content
import com.mobile.goclean.components.home.reportSummary
import com.mobile.goclean.components.topBar
import com.mobile.goclean.theme.Green600

@Composable
fun HomeScreen(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = navBackStackEntry?.destination?.route ?: "login_screen"
    Scaffold(
        topBar = { topBar(navController = navController) },
        content = { paddingValues ->
            content(modifier = Modifier.padding(paddingValues))
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

@Preview
@Composable
private fun prev() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}
