package tutorials.lesson11

import base.lastChar
import tutorials.lesson10.println

/*
 * 
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */

fun main() {
    printNames("kmss")
    // 调用函数时给定参数名
    printNames("kmss", penName = "shuo")

    "kmss".lastChar()

    multiple().println()



}

// 函数参数默认值
fun printNames(realName: String, nickName: String = "苦木说说", penName: String = "kumushuoshuo") {
    println("realName=$realName,nickName=$nickName,penName=$penName")
}

// 单表达式函数
fun add(num1: Int, num2: Int) = num1 + num2
fun min(num1: Int, num2: Int) = if (num1 < num2) num1 else num2


// 可变参数
fun multiple(vararg nums: Int): Int {
    if (nums.isEmpty()) return 0
    return nums.fold(1) { acc, it ->
        acc * it
    }
}

fun testAnonymousFun(){
    // 匿名函数
    (fun(x: Int, y: Int): Int {
        val result = x + y
        println("sum:$result")
        return result
    })(1, 9)
}
