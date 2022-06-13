package com.example.stock.data.csv

import java.io.InputStream

/**
 * @date: 2022/6/13
 * @author: chenyongda3
 * Description:
 */
interface CSVParser<T> {
    suspend fun  parse(stream: InputStream): List<T>
}