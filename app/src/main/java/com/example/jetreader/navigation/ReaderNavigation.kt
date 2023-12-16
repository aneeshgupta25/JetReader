package com.example.jetreader.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetreader.screens.book.BookDetailScreen
import com.example.jetreader.screens.book.BookDetailsViewModel
import com.example.jetreader.screens.categories.CategoriesScreen
import com.example.jetreader.screens.categories.CategoriesViewModel
import com.example.jetreader.screens.home.HomeScreen
import com.example.jetreader.screens.home.HomeScreenViewModel
import com.example.jetreader.screens.home.SearchViewModel
import com.example.jetreader.screens.login.LoginScreen
import com.example.jetreader.screens.login.LoginViewModel
import com.example.jetreader.screens.onboard.OnboardScreen
import com.example.jetreader.screens.register.RegisterScreen
import com.example.jetreader.screens.register.RegisterViewModel
import com.example.jetreader.screens.splash.SplashScreen

@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    val searchViewModel = hiltViewModel<SearchViewModel>()
    val homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
    val bookDetailsViewModel = hiltViewModel<BookDetailsViewModel>()
    val loginViewModel = hiltViewModel<LoginViewModel>()
    val categoriesViewModel = hiltViewModel<CategoriesViewModel>()
    val registerViewModel = hiltViewModel<RegisterViewModel>()
    NavHost(
        navController = navController,
        startDestination = ReaderScreens.SplashScreen.name
    ) {
        composable(route = ReaderScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        composable(route = ReaderScreens.OnBoardScreen.name) {
            OnboardScreen(navController = navController)
        }
        composable(route = ReaderScreens.RegisterScreen.name) {
            RegisterScreen(
                registerViewModel = registerViewModel,
                navController = navController
            )
        }
        composable(route = ReaderScreens.LoginScreen.name) {
            LoginScreen(
                loginViewModel = loginViewModel,
                navController = navController
            )
        }
        composable(route = ReaderScreens.HomeScreen.name) {
            HomeScreen(
                searchViewModel = searchViewModel,
                navController = navController,
                homeScreenViewModel = homeScreenViewModel
            )
        }
        composable(
            route = ReaderScreens.BookDetailsScreen.name + "/{book_id}/{firestore_id}",
            arguments = listOf(navArgument("book_id") { type = NavType.StringType },
                navArgument("firestore_id") { type = NavType.StringType }
            )
        ) { navBackStackEntry ->
            val bookId = navBackStackEntry.arguments!!.getString("book_id")!!
            val fireStoreId = navBackStackEntry.arguments!!.getString("firestore_id")
            BookDetailScreen(
                bookId = bookId,
                fireStoreId = fireStoreId,
                bookDetailsViewModel = bookDetailsViewModel,
                navController = navController
            )
        }
        composable(
            route = ReaderScreens.CategoriesScreen.name + "/{data_type}",
            arguments = listOf(navArgument("data_type") { type = NavType.StringType })
        ) { navBackStackEntry ->
            CategoriesScreen(
                dataType = navBackStackEntry.arguments!!.getString("data_type")!!,
                homeScreenViewModel = homeScreenViewModel,
                categoriesViewModel = categoriesViewModel,
                navController = navController
            )
        }
    }
}