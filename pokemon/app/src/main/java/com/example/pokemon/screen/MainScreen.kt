package com.example.pokemon.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pokemon.viewmodel.PokemonViewModel

@Composable
fun MainScreen(
    onPokemonClick: (String) -> Unit,
    viewModel: PokemonViewModel = hiltViewModel(),
) {
    val items = viewModel.pokemonList.collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(
            count = items.itemCount,
        ) { idx ->
            items[idx]?.let {
                Card(
                    elevation = 8.dp,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Column {
                            Text("포켓몬: ${it.name}")
                            Text(
                                text = it.url,
                                Modifier.alpha(0.4f),
                            )
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                        Button(onClick = {
                            onPokemonClick(it.url)
                        }) {
                            Text("보기")
                        }
                    }
                }
            }
        }
        when (val state = items.loadState.append) {
            is LoadState.Error -> {
                Log.e("LoadState_Error::", state.toString())
            }
            is LoadState.Loading -> {
                Log.e("LoadState_Loading::", state.toString())
            }
            else -> {
                Log.e("LoadState_Else::", state.toString())
            }
        }
    }
}
