@file:JvmName("TopHelper")
package tutorials.lesson11

/*
 * 
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */

// 顶级函数（类的外部）
fun max(num1: Int, num2: Int): Int {
    return if (num1 > num2) num1 else num2
}