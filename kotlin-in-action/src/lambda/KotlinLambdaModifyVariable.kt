package lambda

/**
 * desc: 修改Lambda外面的变量 演示
 *
 * Created by Chiclaim on 2018/09/22
 */

fun printGoods(prefix: String, goods: List<String>) {
    var count: Int = 0
    goods.forEach { goodName ->
        count++
        println("$prefix  $goodName")
    }
    println("goods count: $count")
}

fun main(args: Array<String>) {
    printGoods("china", listOf("telephone", "tv"))
}