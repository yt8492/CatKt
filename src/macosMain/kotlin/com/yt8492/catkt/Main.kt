package com.yt8492.catkt

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.vararg

fun main(args: Array<String>) {
    val parser = ArgParser("CatKt")
    val filePaths by parser.argument(ArgType.String, description = "Input file list").vararg()
    parser.parse(args)
    val stringBuilder = StringBuilder()
    filePaths.forEach { filePath ->
        FileLineInputIterable(filePath).forEach {
            stringBuilder.appendln(it)
        }
    }
    print(stringBuilder.toString())
}
