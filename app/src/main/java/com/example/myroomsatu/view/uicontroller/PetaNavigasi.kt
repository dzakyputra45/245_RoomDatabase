package com.example.myroomsatu.view.uicontroller

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myroomsatu.view.DetailSiswaScreen
import com.example.myroomsatu.view.EditSiswaScreen
import com.example.myroomsatu.view.EntrySiswaScreen
import com.example.myroomsatu.view.HomeScreen
import com.example.myroomsatu.view.route.DestinasiDetailSiswa
import com.example.myroomsatu.view.route.DestinasiDetailSiswa.itemIdArg
import com.example.myroomsatu.view.route.DestinasiEditSiswa
import com.example.myroomsatu.view.route.DestinasiEntry
import com.example.myroomsatu.view.route.DestinasiHome

@Composable
fun SiswaApp(navController: NavHostController = rememberNavController(), modifier: Modifier){
    HostNavigasi(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier)
{
    NavHost(navController=navController, startDestination = DestinasiHome.route, modifier = Modifier)
    {

        //Route Home
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(route = DestinasiEntry.route) },
                navigateToItemDetail = { navController.navigate(route = "${DestinasiDetailSiswa.route}/${it}") }
            )
        }

        //Route Entry
        composable(DestinasiEntry.route) {
            EntrySiswaScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

        //Route Detail
        composable(
            route = DestinasiDetailSiswa.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailSiswa.itemIdArg) {
                    type = NavType.IntType
                }
            )
        ) {
            DetailSiswaScreen(
                navigateToEditItem = { /* TODO: nanti arahkan ke halaman edit */ },
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }

        //Route Edit
        composable(
            route = "${DestinasiEditSiswa.route}/{${DestinasiEditSiswa.itemIdArg}}",
            arguments = listOf(
                navArgument(DestinasiEditSiswa.itemIdArg) { type = NavType.IntType }
            )
        ) {
            EditSiswaScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.popBackStack() }
            )
        }
    }
}

