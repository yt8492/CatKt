package com.yt8492.catkt

import kotlinx.cinterop.IntVar
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKStringFromUtf32
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.vararg
import platform.posix.fclose
import platform.posix.fgetws
import platform.posix.fopen
import platform.posix.perror

fun main(args: Array<String>) {
    val parser = ArgParser("CatKt")
    val filePaths by parser.argument(ArgType.String, description = "Input file list").vararg()
    parser.parse(args)
    val stringBuilder = StringBuilder()
    filePaths.forEach { filePath ->
        val file = fopen(filePath, "r")
        if (file == null) {
            perror("cannot open input file $filePath")
            return
        }
        try {
            memScoped {
                val bufferLength = 64 * 1024
                val buffer = allocArray<IntVar>(bufferLength)
                while (true) {
                    val nextLine = fgetws(buffer, bufferLength, file)?.toKStringFromUtf32()
                    if (nextLine.isNullOrEmpty()) {
                        break
                    }
                    stringBuilder.append(nextLine)
                }
            }
        } finally {
            fclose(file)
        }
    }
    print(stringBuilder.toString())
}
