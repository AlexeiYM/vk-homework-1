package com.example.homework1

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Screen()
        }
    }
}

@Composable
private fun Screen() {
    val list = rememberSaveable { mutableStateListOf<Int>() }
    val columns =
        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) 3 else 4
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier
                .weight(1f)
        ) {
            items(
                items = list,
                key = { it }
            ) { item ->
                Square(item)
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.End)
                .padding(10.dp)
                .size(50.dp),
            onClick = { list.add(list.size + 1) }
        ) {
            Text(
                text = stringResource(R.string.plus),
                fontSize = 20.sp
            )
        }
    }
}

@Composable
private fun Square(number: Int) {
    val backgroundColor: Color =
        if (number % 2 == 0) colorResource(R.color.red)
        else colorResource(R.color.blue)
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(8.dp)
            .background(backgroundColor)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = number.toString(),
            color = Color.White
        )
    }
}