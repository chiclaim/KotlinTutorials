package higher_order_function

/*
 * Function types with receiver
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */

public inline fun <T> T.apply3(block: Int.() -> Unit): T {
    // Function types with receiver
    // block: Int.() -> Unit
    // 相当于 Int 类上扩展了一个名为 block 的函数类型
    // 所以可以使用整型来调用这个 block
    // 直接调用 block 会报错，需要一个 receiver 来调用
    1.block()
    return this
}
public inline fun <T> T.also2(block: (T) -> Unit): T {
    block(this)
    return this
}
fun main() {
    "".apply {

    }
    "".apply3 {
        // this 就是 1
        println("apply3 receiver = $this")
    }

    // public inline fun <T, R> with(receiver: T, block: T.() -> R): R
    // public inline fun <T> T.apply(block: T.() -> Unit): T
    // public inline fun <T> T.also(block: (T) -> Unit): T
    // public inline fun <T, R> T.let(block: (T) -> R): R
    // public inline fun <R> run(block: () -> R): R
    // public inline fun <T, R> T.run(block: T.() -> R): R

}