@file:JvmName("KtException")
package tutorials.lesson26

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

/*
 * 
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */

fun readFile(path:String){
    throw FileNotFoundException("")
}

// Java 调用
@kotlin.jvm.Throws(IOException::class)
fun readFile2(path:String){

}

fun main() {
    readFile("file")


    File("").forEachLine { println(it) }
}