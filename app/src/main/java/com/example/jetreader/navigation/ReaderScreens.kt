package com.example.jetreader.navigation

enum class ReaderScreens {
    SplashScreen,
    OnBoardScreen,
    LoginScreen,
    RegisterScreen,
    HomeScreen,
    BookDetailsScreen,
    CategoriesScreen;

    companion object {
        fun fromRoute(route: String?): ReaderScreens =
            when(route?.substringBefore("/")) {
                SplashScreen.name -> SplashScreen
                OnBoardScreen.name -> OnBoardScreen
                LoginScreen.name -> LoginScreen
                RegisterScreen.name -> RegisterScreen
                HomeScreen.name -> HomeScreen
                BookDetailsScreen.name -> BookDetailsScreen
                CategoriesScreen.name -> CategoriesScreen
                null -> SplashScreen
                else -> throw IllegalArgumentException("Route $route is not recognised")
            }
    }
}