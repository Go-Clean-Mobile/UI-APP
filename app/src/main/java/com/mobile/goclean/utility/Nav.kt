package com.mobile.goclean.utility

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mobile.goclean.screens.AddScreen
import com.mobile.goclean.screens.HistoryScreen
import com.mobile.goclean.screens.HomeScreen
import com.mobile.goclean.screens.LoginScreen
import com.mobile.goclean.screens.ProfileScreen
import com.mobile.goclean.screens.RegisterScreen
import com.mobile.goclean.screens.SplashScreen

@Composable
fun Nav() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }

        composable("login_screen") {
            LoginScreen(navController = navController)
        }
        composable("register_screen") {
            RegisterScreen(navController = navController)
        }

        composable("home_screen") {
            HomeScreen(navController = navController)
        }

        composable("profile_screen") {
            ProfileScreen(navController = navController)
        }

        composable("add_screen") {
            AddScreen(navController = navController)
        }

        composable("history_screen") {
            HistoryScreen(navController = navController)
        }
    }
}
