package com.example.composedi.github

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun GithubScreen(viewModel: GithubViewModel = hiltViewModel()) {
    LazyColumn {
        item {
            Button(onClick = {
                viewModel.getRepos()
            }) {
                Text("레포지토리 가져오기")
            }
        }
        items(viewModel.repos) {
            Text(it.name)
        }
    }
}
