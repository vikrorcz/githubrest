package com.bura.githubrest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bura.githubrest.screens.MainView
import com.bura.githubrest.screens.MainViewModel
import com.bura.githubrest.screens.UserView
import com.bura.githubrest.screens.UserViewModel
import com.bura.githubrest.screens.util.Screen
import com.bura.githubrest.ui.theme.GithubrestTheme

//MVVM - model, view, view model
class MainActivity : ComponentActivity() {

    //private val mainViewModel: MainViewModel by viewModels()
    //private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GithubrestTheme {
                val navController = rememberNavController()

                NavHost(navController, Screen.MainScreen.name) {//starting destination
                    composable(Screen.MainScreen.name) {
                        val mainViewModel: MainViewModel by viewModels()
                        MainView(navController, mainViewModel)
                    }

                    composable(
                        "${Screen.UserScreen.name}/{username}",
                        arguments = listOf(navArgument("username") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val viewModel = UserViewModel(backStackEntry.arguments?.getString("username") ?: "")
                        UserView(navController, viewModel)
                    }
                }
            }
        }
    }
}

