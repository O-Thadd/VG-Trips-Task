package com.app.vgtask.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.vgtask.ui.screens.home.HomeScreen
import com.app.vgtask.ui.models.NavDestination
import com.app.vgtask.ui.screens.trip.TRIP_ID_KEY
import com.app.vgtask.ui.screens.trip.TripScreen

@Composable
fun VGTaskApp() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavDestination.HOME.route
    ){

        composable(NavDestination.HOME.route){
            HomeScreen(
                goToTrip = {
                    navController.navigate("${NavDestination.TRIP.route}/$it")
                }
            )
        }
        
        composable(route = "${NavDestination.TRIP.route}/{${TRIP_ID_KEY}}"){
            TripScreen(
                goBackToHome = { navController.popBackStack() }
            )
        }

    }
}