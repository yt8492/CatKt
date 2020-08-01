package com.yt8492.catkt

import kotlinx.cinterop.toKString
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.vararg

fun main(args: Array<String>) {
    val parser = ArgParser("CatKt")
    val filePaths by parser.argument(ArgType.String, description = "Input file list").vararg()
    parser.parse(args)
    val stringBuilder = StringBuilder()
    filePaths.forEach { filePath ->
        val fileInputIterable = FileInputIterable(filePath)
        fileInputIterable.map {
            it.toByte()
        }.toByteArray()
            .toKString()
            .let {
                stringBuilder.appendln(it)
            }
    }
    println(stringBuilder.toString())
}
