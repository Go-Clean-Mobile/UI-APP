package com.mobile.goclean.utility

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mobile.goclean.screens.addScreen
import com.mobile.goclean.screens.historyScreen
import com.mobile.goclean.screens.homeScreen
import com.mobile.goclean.screens.loginScreen
import com.mobile.goclean.screens.profileScreen
import com.mobile.goclean.screens.registerScreen
import com.mobile.goclean.screens.splashScreen

@Composable
fun Nav() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            splashScreen(navController = navController)
        }

        composable("login_screen") {
            loginScreen(navController = navController)
        }

        composable("register_screen") {
            registerScreen(navController = navController)
        }

        composable("home_screen") {
            homeScreen(navController = navController)
        }

        composable("profile_screen") {
            profileScreen(navController = navController)
        }

        composable("add_screen") {
            addScreen(navController = navController)
        }

        composable("history_screen") {
            historyScreen(navController = navController)
        }
    }
}
