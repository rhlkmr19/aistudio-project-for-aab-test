package com.example.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ui.screens.AlphabetsScreen
import com.example.ui.screens.ColorsScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.NumbersScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onNavigateToAlphabets = { navController.navigate("alphabets") },
                onNavigateToNumbers = { navController.navigate("numbers") },
                onNavigateToColors = { navController.navigate("colors") }
            )
        }
        
        composable("alphabets") {
            AlphabetsScreen(onBack = { navController.popBackStack() })
        }
        
        composable("numbers") {
            NumbersScreen(onBack = { navController.popBackStack() })
        }
        
        composable("colors") {
            ColorsScreen(onBack = { navController.popBackStack() })
        }
    }
}
