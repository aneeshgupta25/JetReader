package com.example.jetreader.navigation

enum class ReaderScreens {
    SplashScreen,
    OnBoardScreen,
    LoginScreen,
    RegisterScreen;

    companion object {
        fun fromRoute(route: String?): ReaderScreens =
            when(route?.substringBefore("/")) {
                SplashScreen.name -> SplashScreen
                OnBoardScreen.name -> OnBoardScreen
                LoginScreen.name -> LoginScreen
                RegisterScreen.name -> RegisterScreen
                null -> SplashScreen
                else -> throw IllegalArgumentException("Route $route is not recognised")
            }
    }
}