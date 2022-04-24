package com.example.composelayout.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.mycompose.ui.theme.AppTheme
import kotlinx.coroutines.launch

/**
 * Created by cyd on 2022/4/23
 **/

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SimpleLazyList() {
    val state = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val listSize = 100

    LazyColumn(state = state) {
        stickyHeader {
            Row(horizontalArrangement = Arrangement.SpaceAround) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            state.animateScrollToItem(0)
                        }
                    }) {
                    Text("To first")
                }

                Button(onClick = {
                    coroutineScope.launch {
                        state.animateScrollToItem(listSize - 1)
                    }
                }) {
                    Text("To Last")
                }
            }
        }
        items(listSize) {
            ImageListItem(index = it)
        }
    }
}


@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "",
            modifier = Modifier.size(50.dp)
        )

        Spacer(modifier = Modifier.size(10.dp))

        Text("item $index", style = MaterialTheme.typography.subtitle1)
    }
}

@Preview(showBackground = true)
@Composable
fun ImageListItemPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            SimpleLazyList()
        }
    }
}