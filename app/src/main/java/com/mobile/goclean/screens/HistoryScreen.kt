package com.mobile.goclean.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mobile.goclean.components.bottomBar
import com.mobile.goclean.components.history.historyItems
import com.mobile.goclean.components.topBar
import com.mobile.goclean.theme.BackgroundGradient
import com.mobile.goclean.theme.Green600

@Composable
fun historyScreen(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = navBackStackEntry?.destination?.route ?: "history_screen"
    println("Route saat ini : $currentScreen")
    Scaffold(
        topBar = { topBar(navController = navController) },
        content = { paddingValues ->
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(BackgroundGradient)
                        .padding(paddingValues)
                        .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    "History",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 20.dp),
                )
                Column(
                    Modifier.verticalScroll(rememberScrollState()),
                ) {
                    historyItems(
                        tanggal = "17/08/2024",
                        jumlahKoin = 88,
                        type = 1,
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    historyItems(
                        tanggal = "18/08/2024",
                        jumlahKoin = 42,
                        type = 2,
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    historyItems(
                        tanggal = "19/08/2024",
                        jumlahKoin = 76,
                        type = 1,
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    historyItems(
                        tanggal = "20/08/2024",
                        jumlahKoin = 65,
                        type = 2,
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    historyItems(
                        tanggal = "20/08/2024",
                        jumlahKoin = 25,
                        type = 2,
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    historyItems(
                        tanggal = "20/08/2024",
                        jumlahKoin = 55,
                        type = 2,
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    historyItems(
                        tanggal = "21/08/2024",
                        jumlahKoin = 63,
                        type = 1,
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    historyItems(
                        tanggal = "21/08/2024",
                        jumlahKoin = 63,
                        type = 1,
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    historyItems(
                        tanggal = "21/08/2024",
                        jumlahKoin = 63,
                        type = 1,
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    historyItems(
                        tanggal = "21/08/2024",
                        jumlahKoin = 63,
                        type = 1,
                    )
                }
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

@Preview
@Composable
private fun prev() {
    val navController = rememberNavController()
    historyScreen(navController = navController)
}
