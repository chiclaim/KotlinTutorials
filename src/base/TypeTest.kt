package base

/**
 * Desc: 数据类型测试
 * Created by Chiclaim on 2018/9/18.
 */

//会编译成基本类型int，同时会生成getter和setter方法
var level: Int = 10

//会编译成一个Integer类型
var number: Int? = null

//private static Object autoBox = Integer.valueOf(1);
var autoBox: Any = 1

//返回值Int编译后最终变成基本类型int
fun getAge(): Int {
    return 0
}

//集合里的元素都是Integer类型
fun getScores(): List<Int> {
    return listOf(22, 90, 50)
}

//会编译成一个Integer[]
fun getHeight(): Array<Int> {
    return arrayOf(170, 180, 190)
}

//会编译成一个int[]
fun getHeight2(): IntArray {
    return intArrayOf(170, 180, 190)
}

