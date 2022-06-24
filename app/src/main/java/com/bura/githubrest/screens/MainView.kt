package com.bura.githubrest.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bura.githubrest.screens.util.Screen
import com.bura.githubrest.screens.util.TextComposable
import com.bura.githubrest.ui.theme.GithubrestTheme

@Composable
fun MainView(navController: NavController, viewModel: MainViewModel) {

    GithubrestTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment  =  Alignment.CenterHorizontally,
                ) {
                TextComposable("GitHub REST API")
                TextFieldComposable(viewModel)
                ButtonComposable(viewModel, navController)
            }
        }
    }
}

@Composable
fun TextFieldComposable(viewModel: MainViewModel) {
    var endText by rememberSaveable { mutableStateOf("") }
    TextField(
        singleLine = true,
        value = endText,
        onValueChange = { endText = it },
        shape = RoundedCornerShape(20.dp),
        label = { Text("Username") }
    )
    viewModel.setName(endText)
}

@Composable
fun ButtonComposable(viewModel: MainViewModel, navController: NavController) {
    val myContext = LocalContext.current

    Button(onClick = {
        if (viewModel.name.value.isNotEmpty()) {
            navController.navigate("${Screen.UserScreen.name}/${viewModel.name.value}")
        } else {
           errorToast(myContext)
        }

    }) {
        Text(text = "Search")
    }
}


private fun errorToast(context: Context){
    Toast.makeText(context, "Username must not be empty", Toast.LENGTH_LONG).show()
}