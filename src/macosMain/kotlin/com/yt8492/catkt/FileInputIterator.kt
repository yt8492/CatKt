package com.yt8492.catkt

import platform.posix.EOF
import platform.posix.fclose
import platform.posix.fgetc
import platform.posix.fopen

class FileInputIterator(
    filePath: String
) : Iterator<Int> {

    private val filePointer = fopen(filePath, "r")
        ?: throw NullPointerException("file not found")
    private var current: Int

    init {
        try {
            current = fgetc(filePointer)
        } catch (e: Exception) {
            fclose(filePointer)
            throw e
        }
    }

    override fun hasNext(): Boolean {
        return current != EOF
    }

    override fun next(): Int {
        val retVal = current
        try {
            current = fgetc(filePointer)
            if (!hasNext()) {
                fclose(filePointer)
            }
        } catch (e: Exception) {
            fclose(filePointer)
            throw e
        }
        return retVal
    }
}
