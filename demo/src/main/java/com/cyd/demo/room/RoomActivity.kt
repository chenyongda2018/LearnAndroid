package com.cyd.demo.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.cyd.demo.databinding.ActivityRoomBinding

/**
 * Jetpack Room 示例
 */
class RoomActivity : AppCompatActivity() {

    private var _binding: ActivityRoomBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}