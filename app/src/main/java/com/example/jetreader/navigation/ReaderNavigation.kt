package com.example.jetreader.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetreader.screens.login.LoginScreen
import com.example.jetreader.screens.onboard.OnboardScreen
import com.example.jetreader.screens.register.RegisterScreen
import com.example.jetreader.screens.splash.SplashScreen

@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = ReaderScreens.SplashScreen.name) {
        composable(route = ReaderScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        composable(route = ReaderScreens.OnBoardScreen.name) {
            OnboardScreen(navController = navController)
        }
        composable(route = ReaderScreens.RegisterScreen.name) {
            RegisterScreen()
        }
        composable(route = ReaderScreens.LoginScreen.name) {
            LoginScreen()
        }
    }
}