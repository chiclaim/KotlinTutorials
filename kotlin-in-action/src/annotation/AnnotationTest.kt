package annotation

/**
 * Desc:
 * Created by Chiclaim on 2018/10/15.
 */

@Deprecated("Use removeAt(index) instead.", ReplaceWith("removeAt(index)"))
fun remove(index: Int) {
}


fun main(args: Array<String>) {
    remove(0)
}