package tutorials.lesson10

import higher_order_function.joinToString
import java.util.*

fun Any?.log() {
    println(this.toString().padStart(50, '-'))
}

fun Any?.println() {
    println(this)
}

/**
 *
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */

private const val NAME = "苦木说说"

fun main() {
    buildString()
    compareStr(NAME)
    operateString()
    testStringCase()
}

// 构建 string
fun buildString() {

    var str = "Hello " + NAME

    // string interpolation
    str = "Hello $NAME"
    str = "Hello ${NAME.length}"

    // java StringBuilder
    str = buildString {
        append("Hello").append(NAME)
    }

    // create string from collection
    str = NAME.toList().joinToString("-") // 苦-木-说-说
}

fun compareStr(str: String?) {

    if (str == "YELLOW") {
        println("matched!")
    }

    if(str.equals("YELLOW",true)){
        println("matched!")
    }

    str?.endsWith("xx")
    // Java 不支持 endsWith 大小写
    str?.endsWith("xx",false)
    // Java 不支持 startsWith 大小写
    str?.startsWith("xx",true)
}

fun operateString() {
    NAME[0]
    NAME.first() // NoSuchElementException
    NAME.firstOrNull()

    NAME[NAME.length-1]
    NAME.last() // NoSuchElementException
    NAME.lastOrNull()
    NAME.lastOrNull {
        it == '说'
    }.println()

    "ifBlank".log()
    val blank = "   "
    blank.ifBlank { "this is a default value" }.println()
    blank.ifEmpty { "this is a default value" }.println()

    // 删除前面 n 个字符串
    println(NAME.drop(1))
    // 删除后面 n 个字符串
    println(NAME.dropLast(1))

    "removeSurrounding".log()
    "##苦木##说说##".removeSurrounding("##").println()

    "split".log()
    "苦木.说说".split(".").println()


    // Pattern.compile("\\w*\\d+\\w*");
    Regex("""\w*\d+\w*""") // raw string


    "multiline".log()
    multiline()


    // C://ddd/dd/cat.jpg
    "get file from path".log()
    val fullPath = "/Users/yuzhiqiang/Dev/Workspace/KotlinTutorials/cat.jpg"
    fullPath.substring(fullPath.lastIndexOf('/') + 1).println()
    fullPath.removeRange(0..fullPath.lastIndexOf('/')).println()
    fullPath.substringAfterLast('/').println()

    "removeSuffix".log()
    // 获取文件名，不包括后缀
    listOf("cat1.jpg", "cat2.jpg", "cat3.jpg").map {
        it.removeSuffix(".jpg")
    }.forEach(::println)

    // 获取文件名，不包括后缀
    "substringBefore".log()
    listOf("cat.jpg", "dog.png", "boy.gif").map {
        it.substringBefore(".")
    }.forEach(::println)


}

fun testStringCase() {
    val name = "kumushuoshuo"
    println(name.capitalize())
    println(name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })

    //println(name.toUpperCase())
    println(name.uppercase(Locale.getDefault()))

    //println(name.toLowerCase())
    println(name.lowercase(Locale.getDefault()))
}

fun multiline() {
    println(
        """
    Not
    trimmed
    text
    """
    )

    println(
        """
    Trimmed
    text
    """.trimIndent()
    )

    println()

    val a = """Trimmed to margin text:
          |if(a > 1) {
          |    return a
          |}""".trimMargin()
    println(a)

    println()

    val a2 = """Trimmed to margin text:
          -if(a > 1) {
          -    return a
          -}""".trimMargin("-")
    println(a2)

    val a3 = """Trimmed to margin text:
          if(a > 1) {
              return a
          }"""
    println(a3)
}
