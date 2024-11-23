package com.mobile.goclean.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.mobile.goclean.components.history.historyItem
import com.mobile.goclean.components.topBar
import com.mobile.goclean.network.History
import com.mobile.goclean.theme.BackgroundGradient
import com.mobile.goclean.viewmodel.HistoryViewModel

@Composable
fun historyScreen(
    navController: NavController,
    viewModel: HistoryViewModel = HistoryViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.fetchUserHistory()
    }

    val currentScreen =
        navController
            .currentBackStackEntryAsState()
            ?.value
            ?.destination
            ?.route ?: "history_screen"
    println("Current Route: $currentScreen")

    Scaffold(
        topBar = { topBar(navController) },
        bottomBar = {
            bottomBar(
                navController = navController,
                currentScreen = currentScreen,
            )
        },
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
                    text = "History",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 20.dp),
                )

                when {
                    uiState.error != null -> errorMessage(uiState.error)
                    uiState.histories.isEmpty() -> emptyHistoryMessage()
                    else -> historyList(uiState.histories)
                }
            }
        },
        contentColor = Color.White,
    )
}

@Composable
fun errorMessage(message: String?) {
    Text(
        text = message ?: "Error",
        color = Color.Red,
        style = MaterialTheme.typography.bodyLarge,
    )
}

@Composable
fun emptyHistoryMessage() {
    Text(
        text = "No history available",
        style = MaterialTheme.typography.bodyLarge,
        color = Color.Gray,
    )
}

@Composable
fun historyList(histories: List<History>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(histories) { history ->
            historyItem(
                date = history.date,
                points = history.points,
                type =
                    when (history.category) {
                        "1" -> 1 // Organic
                        "2" -> 2 // Inorganic
                        else -> 0
                    },
            )
        }
    }
}

@Preview
@Composable
private fun previewHistoryScreen() {
    val navController = rememberNavController()
    historyScreen(navController = navController)
}
