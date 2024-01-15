package com.example.movieworld.navcontrollerPackage

import androidx.compose.runtime.Composable
import androidx.navigation.NavType

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieworld.movieDetails.OpenImage
import com.example.movieworld.MyApp
import com.example.movieworld.MovieFullInfo


@Composable
fun NavController(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {

        composable(route = "main") {

            MyApp(navController)
        }


        composable(route = "DetailsScreen/{id}",
            listOf(navArgument("id") { type = NavType.StringType })
        ) {

            val id = it.arguments!!.getString("id")

            if (id != null) {
                MovieFullInfo(id = id,navController)
            }

        }

        composable(route = "OpenImage/{EncodedUrl}"){

            val url = it.arguments!!.getString("EncodedUrl")

            if (url != null) {
                OpenImage(url = url)
            }

        }
    }


}