package com.bura.githubrest.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.bura.githubrest.R
import com.bura.githubrest.ui.theme.GithubrestTheme

@Composable
fun UserView(navController: NavController, viewModel: UserViewModel) {

    GithubrestTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background

        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                ImageComposable(viewModel)
                UserComposable(viewModel)
            }
        }
    }
}

@Composable
fun UserComposable(viewModel: UserViewModel) {
    val title = viewModel.login.collectAsState(initial = "")
    val name = viewModel.name.collectAsState(initial = "")
    val id = viewModel.id.collectAsState(initial = "")
    val pubicRepos = viewModel.publicRepos.collectAsState(initial = "")

    Text("Login: " + title.value +
            "\nName: " + name.value +
            "\nid: " + id.value +
            "\nPublic repos: " + pubicRepos.value
    )

}


@Composable
fun ImageComposable(viewModel: UserViewModel) {
    val url = viewModel.avatarUrl.collectAsState(initial = "")

    AsyncImage(
        model = url.value,
        placeholder = painterResource(R.drawable.ic_launcher_background),
        contentDescription = null,
    )
}

