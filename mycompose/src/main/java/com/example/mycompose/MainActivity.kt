package com.example.mycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycompose.ui.layout.Chip
import com.example.mycompose.ui.layout.MyColumnLayoutDemo
import com.example.mycompose.ui.layout.StaggeredGrid
import com.example.mycompose.ui.layout.topics
import com.example.mycompose.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    StaggeredGrid(rows = 5) {
                        for(topic in topics) {
                            Chip(text = topic, modifier = Modifier.padding(8.dp))
                        }
                    }
                }
            }
        }
    }
}
