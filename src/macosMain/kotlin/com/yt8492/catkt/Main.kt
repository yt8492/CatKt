package com.yt8492.catkt

import kotlinx.cinterop.toKString
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.vararg

fun main(args: Array<String>) {
    val parser = ArgParser("main")
    val filePaths by parser.argument(ArgType.String, description = "Input file").vararg()
    parser.parse(args)
    filePaths.forEach { filePath ->
        val fileInputIterable = FileInputIterable(filePath)
        fileInputIterable.map {
            it.toByte()
        }.toByteArray()
            .toKString()
            .let(::println)
    }
}
