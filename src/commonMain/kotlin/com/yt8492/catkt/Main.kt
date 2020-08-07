package com.yt8492.catkt

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlinx.cli.vararg

fun main(args: Array<String>) {
    val parser = ArgParser("CatKt")
    val filePaths by parser.argument(ArgType.String, description = "Input file list").vararg()
    val number by parser.option(ArgType.Boolean, shortName = "n", description = " Number the output lines, starting at 1.").default(false)
    parser.parse(args)
    val stringBuilder = StringBuilder()
    filePaths.forEach { filePath ->
        FileLineInputIterable(filePath).forEachIndexed { index, line ->
            if (number) {
                val rowNumber = "     ${index + 1}  "
                stringBuilder.append(rowNumber.slice(rowNumber.length - 8 until rowNumber.length))
            }
            stringBuilder.appendln(line)
        }
    }
    print(stringBuilder.toString())
}
