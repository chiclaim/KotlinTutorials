package type

/**
 * Created by dh on 2017/7/13.
 */


fun strLen(s: String) = s.length

var str: String? = null
fun main(args: Array<String>) {
    //strLen(str)
    strLenSafe(null)

    printAllCaps("abc")
    printAllCaps(null)

    printStringLen("abc") //string abc length = 3
    printStringLen(null) //string null length = null
}

fun strLenSafe(s: String?): Int =
        if (s != null) s.length else 0

/**
 * safe call operator ?.
 */
fun printAllCaps(s: String?) {
    val allCaps: String? = s?.toUpperCase()
    println(allCaps)
}

fun printStringLen(s: String?) {
    //val len = s?.length     //?. Safe call
    val len = s?.length ?: 0  //?: Elvis operator
    println("string $s length = $len")
}