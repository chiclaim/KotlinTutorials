import java.util.*
import strings.lastChar as last

fun main(args: Array<String>) {
//    println("hello world")
//    println(max(199, 99))
//    println(max2(199, 99))

//    declareVariables()


//    forLoop()
//    forDownToStep()


//    forMap()


//    println("Kotlin" in "Java".."Scala")

//    baseCollection()

//    namedArguments(name = "Chiclaim")

//    val langs = arrayOf("Java", "Kotlin", "Scale", "Clojure")
//    defaultParameterValues(langs,"[",",","]")
//    val listOfList = listOf("C#", *langs) // `*` 展开操作符
//    println(listOfList.size)
//    println(listOfList)

//    println("Kotlin".last())

    parsePath("C:/tmp/mp4/tmp-movie.mp4")
}

fun parsePath(path: String) {
    val dir = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")

    val fileName = fullName.substringBeforeLast(".")

    val ext = fullName.substringAfterLast(".")
    println("dir = $dir, fileName = $fileName , ext = $ext")
    
}

fun <T> defaultParameterValues(collection: Collection<T>, prefix: String, separator: String, postfix: String): String {

    val sb = StringBuilder().append(prefix)
    for ((index, value) in collection.withIndex()) {
        sb.append(value).append(separator)
    }
    sb.append(postfix)
    return sb.toString()
}


/**
 * 调用方法的时候给参数命名
 */
fun namedArguments(name: String) {
    println(name)
}

/**
 * 集合基本操作
 */
fun baseCollection() {

    //javaClass  =  .getClass
    val list = listOf(1, 2, 3, 4, 5, "string")
    println(list.javaClass) //java.util.Arrays$ArrayList

    val map = mapOf(1 to "one", 2 to "two", 3 to "three")
    println(map.javaClass) //java.util.LinkedHashMap

    val set = setOf(1, 2, 3, 4, 5, 6)
    println(set.javaClass) //java.util.LinkedHashSet

    println("list last = ${list.last()}")

    println("set max = ${set.max()}")

    println(list)
    println(map)
    println(set)

}

fun forList() {

    val list = arrayListOf("xx", "zz")
    for ((index, value) in list.withIndex()) {
        println("$index $value")
    }
}

fun forMap() {
    val binaryReps = TreeMap<Char, String>()
    for (c in 'A'..'Z') {
        binaryReps[c] = Integer.toBinaryString(c.toInt())
    }
    for ((key, value) in binaryReps) {
        println("$key = $value")
    }
}


fun forDownToStep() {
    for (i in 100 downTo 1 step 2) {
        println(i)
    }
}

fun forDownTo() {
    for (i in 100 downTo 1) {
        println(i)
    }
}

fun forLoop() {
    for (i in 1..100) {
        println(i)
    }
}

fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

fun max2(a: Int, b: Int) = if (a > b) a else b

fun declareVariables() {

    //var -- variable
    //val -- value
    //通过val关键字声明的变量，一旦被赋值后便不能被修改，类似Java里final修饰的变量

    val age = 11
    println("Int类型自动推导：" + age)

    val price = 11.2
    println("Double类型自动推导：" + price)

    val a: Int
    a = 100
    println("如果一开始不赋值，需要声明变量类型：" + a)


    val name = "Chilaim"
    println("My name is $name!")
    println("My name is $name. I love \$character!")
    println("I am a ${if (age >= 18) "adult" else "nonage"}")

}