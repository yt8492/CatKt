package com.yt8492.catkt

class FileInputIterable(
    private val filePath: String
) : Iterable<Int> {
    override fun iterator(): Iterator<Int> {
        return FileInputIterator(filePath)
    }
}
