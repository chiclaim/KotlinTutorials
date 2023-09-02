package tutorials.lesson33


fun testForContinue() {
    for (i in 1..10) {
        if (i % 2 == 0) continue
        println(i)
    }
    println("end...")
}

fun testForBreak() {
    for (i in 1..10) {
        if (i % 2 == 0) break
        println(i)
    }
    println("end...")
}

fun testNestForBreak() {
    loop@ for (i in 1..10) {
        for (j in 1..10) {
            if (i % 2 == 0) break@loop
        }
        println(i)
    }
    println("end...")
}

fun testForReturn() {
    for (i in 1..10) {
        if (i % 2 == 0) return
        println(i)
    }
    println("end...")
}

fun testForeachReturn() {
    val list = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    list.forEach { i ->
        if (i % 2 == 0) return
        println(i)
    }
    println("end...")
}

fun testForeachContinue() {
    val list = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    list.forEach { i ->
        // if (i % 2 == 0) continue
        if (i % 2 == 0) return@forEach
        println(i)
    }
    println("end...")
}


fun testForeachBreak() {
    val list = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    run loop@{
        list.forEach { i ->
            // if (i % 2 == 0) break
            if (i % 2 == 0) return@loop
            println(i)
        }
    }
    println("end...")

}

private inline fun normalFunction(action: () -> Unit) {
    action()
}

fun invokeNormalFunction() {
    var age = 0
    // 调用高阶函数
    normalFunction {
        if (age <= 0) return
        println("age:$age")
    }
    println("end!!!")
}

fun invoke2NormalFunction() {
    var age = 0
    // 调用高阶函数
    normalFunction {
        if (age <= 0) return@normalFunction
        println("age:$age")
    }
    println("end!!!")
}

fun invoke3NormalFunction() {
    var age = 0
    // 调用高阶函数
    normalFunction loop@{ // 自定义 label 名字
        if (age <= 0) return@loop // local return
        println("age:$age")
    }
    println("end!!!")
}

fun main() {
    invoke2NormalFunction()
}