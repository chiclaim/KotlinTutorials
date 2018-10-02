package operator_overloading

/**
 * Desc: 解构操作符重载
 * Created by Chiclaim on 2018/9/30.
 */


operator fun Point.component1() = x

operator fun Point.component2() = y


fun getPoint(): Point {
    return Point(1, 2)
}


fun main(args: Array<String>) {
    val p1 = Point(10, 19)
    val (x, y) = p1
    println("x=$x , y=y$y")

    val (x1, y1) = getPoint()
    println("x=$x1 , y=y$y1")


    //解构操作符用于 集合遍历
    val map = hashMapOf("name" to "chiclaim", "address" to "hangzhou")
    for ((key, value) in map) {
        println("$key -> $value")
    }

}








