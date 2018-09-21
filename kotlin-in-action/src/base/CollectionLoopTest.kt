package base

/**
 * Desc: Kotlin集合遍历 演示
 * Created by Chiclaim on 2018/9/18.
 */

fun main(args: Array<String>) {
    testFilterForEach()
}


fun testFilterForEach() {
    val arr = arrayOf(1, 2, 3, 4, 5, 6)

    //filter->foreach
    //执行原理，先把集合的所有元素过滤一遍(filter)，然后整体遍历
    arr.filter {
        System.out.println("filter----")
        it % 2 == 0
    }.forEachIndexed { index, value ->
        System.out.println("" + index + "-" + value)
    }
}