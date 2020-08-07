package com.yt8492.catkt

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKString
import platform.posix.fclose
import platform.posix.fgets
import platform.posix.fopen

actual class FileLineInputIterator(
    filename: String
) : Iterator<String> {

    private val filePointer = fopen(filename, "r")
        ?: throw NullPointerException("file not found")
    private var current = readLine()

    override fun hasNext(): Boolean {
        return current != null
    }

    override fun next(): String {
        val retVal = current ?: error("unknown state")
        current = readLine()
        return retVal
    }

    private fun readLine(): String? {
        return try {
            val stringBuilder = StringBuilder()
            memScoped {
                val buffer = allocArray<ByteVar>(bufferLength)
                while (true) {
                    val nextLine = fgets(buffer, bufferLength, filePointer)?.toKString()
                    if (nextLine == null) {
                        return if (stringBuilder.isEmpty()) {
                            null
                        } else {
                            stringBuilder.toString()
                        }
                    } else {
                        if (nextLine.last() == '\n') {
                            stringBuilder.append(nextLine.dropLast(1))
                            break
                        } else {
                            stringBuilder.append(nextLine)
                        }
                    }
                }
                stringBuilder.toString()
            }
        } catch (e: Exception) {
            fclose(filePointer)
            throw e
        }
    }

    companion object {
        private const val bufferLength = 64 * 1024
    }
}