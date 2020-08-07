package com.yt8492.catkt

import java.io.File

actual class FileLineInputIterator(
    filename: String
) : Iterator<String> {

    private val input = File(filename).bufferedReader()
    private var current = input.readLine()

    override fun hasNext(): Boolean {
        return current != null
    }

    override fun next(): String {
        val retVal = current
        current = input.readLine()
        return retVal
    }
}