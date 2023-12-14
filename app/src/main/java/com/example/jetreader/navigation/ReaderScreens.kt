package com.example.jetreader.navigation

enum class ReaderScreens {
    SplashScreen,
    OnBoardScreen,
    LoginScreen,
    RegisterScreen,
    HomeScreen,
    BookDetailsScreen;

    companion object {
        fun fromRoute(route: String?): ReaderScreens =
            when(route?.substringBefore("/")) {
                SplashScreen.name -> SplashScreen
                OnBoardScreen.name -> OnBoardScreen
                LoginScreen.name -> LoginScreen
                RegisterScreen.name -> RegisterScreen
                HomeScreen.name -> HomeScreen
                BookDetailsScreen.name -> BookDetailsScreen
                null -> SplashScreen
                else -> throw IllegalArgumentException("Route $route is not recognised")
            }
    }
}