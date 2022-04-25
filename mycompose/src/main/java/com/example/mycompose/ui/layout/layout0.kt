package com.example.mycompose.ui.layout

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp

/**
 * @date: 2022/4/24
 * @author: chenyongda3
 * Description:
 */
const val TAG = "CustomLayout"
fun Modifier.firstBaselineToTop(
    firstBaseLineToTop: Dp
) = this.then(
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
        val firstBaseline = placeable[FirstBaseline]
        val placeableY = firstBaseLineToTop.roundToPx() - firstBaseline
        Log.d(
            TAG,
            "firstBaseLineToTop: ${firstBaseLineToTop.roundToPx()}, firstBaseLine: $firstBaseline"
        )
        Log.d(TAG, "placeableHeight: ${placeable.height}")
        Log.d(TAG, "placeableY: $placeableY")
        val height = placeable.height + placeableY
        Log.d(TAG, "all height: $height")

        layout(placeable.width, height) {
            placeable.placeRelative(0, placeableY)
        }
    }
)