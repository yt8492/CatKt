package com.yt8492.catkt

class FileLineInputIterable(
    private val fileName: String
) : Iterable<String> {
    override fun iterator(): Iterator<String> {
        return FileLineInputIterator(fileName)
    }
}