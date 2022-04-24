package com.example.mycompose.ui.layout

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelayout.widget.SimpleLazyList
import com.example.mycompose.ui.theme.AppTheme
import com.example.mycompose.ui.theme.Purple200

/**
 * Created by cyd on 2022/4/24
 **/
@Composable
fun MyColumnLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->

        val placeables = measurables.map {
            it.measure(constraints)
        }

        var yPosition = 0

        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEach { placeable ->
                placeable.placeRelative(0, yPosition)
                yPosition += placeable.height
                Log.d("placeable height:", " ${placeable.height}")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MyColumnLayoutDemo() {
    AppTheme {
        Surface(
//            modifier = Modifier.fillMaxSize(),
            color = Purple200
        ) {
            MyColumnLayout(modifier = Modifier.padding(8.dp)) {
                Text("1")
                Text("2")
            }
        }
    }
}


@Composable
fun MyColumnLayoutPreview() {
    MyColumnLayoutDemo()
}