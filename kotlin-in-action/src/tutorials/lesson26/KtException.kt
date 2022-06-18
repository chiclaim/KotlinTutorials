@file:JvmName("KtException")

package tutorials.lesson26

import java.io.FileNotFoundException
import java.io.IOException

/*
 * 
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/KotlinTutorials
 */

fun readFile(path: String) {
    throw FileNotFoundException("")
}

// 给 Java 调用，如果希望调用 try...catch，可以使用 @Throws 注解
// kotlin 没有 throws 关键字，只有 throw 关键字
@kotlin.jvm.Throws(IOException::class)
fun readFile2(path: String) {

}

fun main() {
    readFile("file")


    // try 是一个表达式
    val num = try {
        "".toInt()
    } catch (e: NumberFormatException) {
        -1
    }
}