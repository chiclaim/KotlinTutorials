package tutorials.lesson04

/*
 * 
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */


fun main() {
    testLoop1()
}

// if
fun testIf(value: Int) {
    if (value > 0) {
        println(">0")
    } else {
        println("<=0")
    }
}

// when的基本语法
fun testWhen(value:Int){
    when(value){
        1->{
            println("1")
        }
        2->{
            println("2")
        }
    }
}

enum class Color{
    BLUE,
    RED,
    YELLOW,
    ORANGE,
    GREEN
}

// 枚举类对象作为when参数
fun testWhen2(color: Color){
    when(color){
        Color.BLUE ->{
            println("BLUE")
        }
        Color.RED->{
            println("RED")
        }
        Color.YELLOW->{
            println("YELLOW")
        }
        Color.ORANGE->{
            println("ORANGE")
        }
        Color.GREEN->{
            println("GREEN")
        }
    }
}

// 任意对象作为when参数
fun testWhen3(c1: Color, c2: Color) {
    when (setOf(c1, c2)) {

        setOf(Color.RED, Color.YELLOW) -> {
            Color.ORANGE
        }

        setOf(Color.YELLOW, Color.BLUE) -> {
            Color.GREEN
        }

        //需要处理 其他 情况
        else -> throw Exception("Dirty color")
    }
}

// 无参数的when表达式
fun testWhen4(c1: Color, c2: Color) = when {
    (c1 == Color.RED && c2 == Color.YELLOW) || (c1 == Color.YELLOW && c2 == Color.RED) -> {
        Color.ORANGE
    }
    (c1 == Color.YELLOW && c2 == Color.BLUE) || (c1 == Color.BLUE && c2 == Color.YELLOW) ->
        Color.GREEN
    else -> throw Exception("Dirty color")
}

// 智能类型转换(smart-casts)
fun testCast(value: Any){
    if(value is String){
        println(value.length)
    }

    when(value){
        is String->{
            print(value.length)
        }
    }
}

fun testWhen5(value:String?){
    when(value){
        "one"->{
            println(1)
        }
    }
}

// 循环结构

fun testLoop1(){
    //while do..while


    // .. 是一个闭区间
    for(i in 0..100){
        print("$i ")
    }

    // until 半闭区间
    println()
    for(i in 0 until 100){
        print("$i ")
    }

    // downTo
    println()
    for(i in 100 downTo 0){
        print("$i ")
    }
    println()
    for(i in 100 downTo 0 step 2){
        print("$i ")
    }




}



