package higher_order_function

fun main() {

    val any = Any()
    println(any)
    val result = any.apply2 {
        println(this)  // The obj of this  has changed
    }
    println(result)


}


fun Any.apply2(block: Any.() -> Unit): String {
    return apply3(Any(), block)
}

// change lambda receiver
fun <R> apply3(r: R, block: R.() -> Unit): String {
    block(r)
    return r.toString()
}