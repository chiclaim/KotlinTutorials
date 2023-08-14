package tutorials.lesson25

import java.io.File

/**
 *
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */


val file = File("kotlin-in-action/src/tutorials/lesson25/file.txt")

fun testWrite() {
    file.writeBytes("kumushuoshuo writeBytes\n".toByteArray())
    //file.writeText("kumushuoshuo writeText\n")
    file.appendBytes("kumushuoshuo appendBytes\n".toByteArray())
    file.appendText("kumushuoshuo appendText\n")
//    file.outputStream().use {
//        it.write("kumushuoshuo outputStream\n".toByteArray())
//    }
//    file.printWriter().use {
//        it.println("kumushuoshuo printWriter")
//    }
//    file.bufferedWriter().use {
//        it.write("kumushuoshuo")
//    }

}

fun testRead() {
    println("forEachLine-------------------->")
    file.forEachLine{
        println(it)
    }
    println("forEachBlock-------------------->")
    file.forEachBlock { buffer: ByteArray, _: Int ->
        print(buffer.decodeToString())
    }
    println("inputStream-------------------->")
    file.inputStream().use {
        var value: Int = it.read()
        while (value != -1) {
            print(value.toChar())
            value = it.read()
        }
    }
    println("inputStream buffer-------------------->")
    file.inputStream().use {
        val byteArray = ByteArray(1024)
        var value: Int = it.read(byteArray,0,byteArray.size)
        while (value != -1) {
            print(byteArray.decodeToString())
            value = it.read(byteArray,0,byteArray.size)
        }
    }
    println("bufferedReader-------------------->")
    file.bufferedReader().use {
        it.readLines().forEach(::println)
    }


}

fun testOther(){
    // 文件后缀
    println(file.extension)

    // 文件名
    println(file.nameWithoutExtension)

    // 遍历文件夹
    file.parentFile.parentFile
        //.walk()// 先访问文件夹，再访问它文件夹里的内容
        .walkBottomUp()// 先访问文件，再访问它文件夹
        .maxDepth(2) // 访问深度
        //.filter {it.extension.equals("txt",true)  }
        .forEach(::println)

    // 文件拷贝
    file.copyTo(File(file.parent,"copy.txt"), overwrite = true)

    // 文件夹拷贝
    file.parentFile.copyRecursively(File(file.parentFile.parentFile.parentFile.parent,"copyRecursively"),true)
}


fun main() {
    //testWrite()
    //testRead()
    testOther()
}