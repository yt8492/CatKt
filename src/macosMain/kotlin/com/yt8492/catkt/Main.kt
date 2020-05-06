package com.yt8492.catkt

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import platform.posix.printf

fun main(args: Array<String>) {
    val parser = ArgParser("main")
    val filePath by parser.argument(ArgType.String, description = "Input file")
    parser.parse(args)
    val fileInputIterable = FileInputIterable(filePath)
    fileInputIterable.forEach {
        printf("%c", it)
    }
    printf("\n")
}
