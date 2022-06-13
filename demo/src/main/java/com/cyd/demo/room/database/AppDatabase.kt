package com.cyd.demo.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cyd.demo.room.dao.UserDao
import com.cyd.demo.room.entity.User

/**
 * @date: 2022/6/10
 * @author: chenyongda3
 * Description:
 * App 的数据库配置类
 */
@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}