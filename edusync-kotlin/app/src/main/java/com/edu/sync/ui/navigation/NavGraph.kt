package com.edu.sync.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.edu.sync.ui.screens.*

@Composable
fun EduSyncNavGraph(
    navController: NavHostController,
    username: String
) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(onLoginSuccess = {
                navController.navigate("dashboard") {
                    popUpTo("login") { inclusive = true }
                }
            })
        }
        
        composable("dashboard") {
            DashboardScreen(
                username = username,
                onNavigate = { screen ->
                    navController.navigate(screen)
                }
            )
        }
        
        composable("notes") {
            NotesScreen(onBack = { navController.popBackStack() })
        }
        
        composable("jcafe") {
            JCafeScreen(onBack = { navController.popBackStack() })
        }
        
        composable("timetable") {
            TimetableScreen(onBack = { navController.popBackStack() })
        }
        
        composable("marketplace") {
            MarketplaceScreen(onBack = { navController.popBackStack() })
        }
        
        composable("carbuddy") {
            CarBuddyScreen(onBack = { navController.popBackStack() })
        }
        
        composable("announcements") {
            AnnouncementsScreen(onBack = { navController.popBackStack() })
        }
        
        composable("shelf") {
            MyShelfScreen(onBack = { navController.popBackStack() })
        }
    }
}
