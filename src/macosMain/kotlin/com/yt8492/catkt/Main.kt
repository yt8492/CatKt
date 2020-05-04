package com.yt8492.catkt

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import platform.posix.EOF
import platform.posix.fclose
import platform.posix.fgetc
import platform.posix.fopen
import platform.posix.putchar

fun main(args: Array<String>) {
    val parser = ArgParser("main")
    val input by parser.argument(ArgType.String, description = "Input file")
    parser.parse(args)
    val file = fopen(input, "r")
    if (file == null) {
        println("file not found.")
        return
    }
    try {
        while (true) {
            val character = fgetc(file)
            if (character == EOF) {
                break
            }
            putchar(character)
        }
    } finally {
        fclose(file)
    }
}
