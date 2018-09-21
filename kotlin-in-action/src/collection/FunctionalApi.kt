package collection

import java.io.File

/**
 * Created by dh on 2017/7/12.
 */

class Book(val title: String, val authors: List<String>)

fun main(args: Array<String>) {
    val list = listOf(1, 2, 3, 4, 5, 6)
    println(list.filter { it % 2 == 0 })

    val people = listOf(Person("Chiclaim", 34), Person("Johnny", 44), Person("Kitty", 44))
    println(people.filter { it.age > 40 })

    val map = listOf(1, 2, 3, 4)
    println(map)
    println(map.map { it * it })

    println("print only names ${people.map { it.name }}")

    println("print name who age > 40 ${people.filter { it.age > 40 }.map { it.name }}")
    println("print name who age > 40 ${people.filter { it.age > 40 }.map(Person::name)}")

    println("oldest person1 ${people.filter { it.age == people.maxBy(Person::age)!!.age }}")

    val maxAge = people.maxBy { it.age }!!.age
    println("oldest person2 ${people.filter { it.age == maxAge }}")

    val numbers = mapOf(0 to "zero", 1 to "one")
    println(numbers.mapValues { it.value.toUpperCase() })


    val canBeInClub34 = { p: Person -> p.age <= 34 }
    println("all ${people.all(canBeInClub34)}")
    println("any ${people.any(canBeInClub34)}")
    println("count  ${people.count(canBeInClub34)}")//不会创建集合，效率更高
    println("count with filter size  ${people.filter(canBeInClub34).size}") //这样创建一个集合
    println("find  ${people.find(canBeInClub34)}")


    //groupBy: converting a list to a map of groups
    println("group by ${people.groupBy { it.age }}")

    val letters = listOf("a", "ab", "b")
    println(letters.groupBy(String::first))//{a=[a, ab], b=[b]}
    println(letters.groupBy(String::length))//{1=[a, b], 2=[ab]}


    //flatMap / flatten

    val b1 = Book("Java入门到放弃", listOf("Chiclaim", "Johnny"))
    val b2 = Book("Kotlin入门到放弃", listOf("Kitty"))
    val books = listOf(b1, b2)
    println(books.flatMap { it.authors }.toSet())


    val string = listOf("abc", "def")
    println(string.flatMap { it.toList() })

    val listOfList = listOf(listOf("Java", "C#"), listOf("Kotlin", "Clojure"))
    println(listOfList.flatten())

    //asSequence
    // people.map(Person::name).filter { it.startsWith("A") } // 会创建两个list,分别保存map和filter的结果
    println("sequence lazy create list ${people.asSequence().map { it.name }.filter { it.startsWith("C") }.toList()}") //asSequence延时创建list


    //不会打印输出
    listOf(1, 2, 3, 4).asSequence().map { print(" map$it ");it * it }.filter { print(" filter($it) ");it % 2 == 0 }
    //输出(1) map1  filter(1)  map2  filter(4)  map3  filter(9)  map4  filter(16)
    listOf(1, 2, 3, 4).asSequence().map { print(" map$it ");it * it }.filter { print(" filter($it) ");it % 2 == 0 }.toList()
    println()
    //输出(2) map1  map2  map3  map4  filter(1)  filter(4)  filter(9)  filter(16)
    listOf(1, 2, 3, 4).map { print(" map$it ");it * it }.filter { print(" filter($it) ");it % 2 == 0 }.toList()
    //@TODO 注意输出(1)和输出(2)的区别(其实就是注意延迟集合操作和费延迟的却别)

    println()
    //1的平方通过map和filter操作，然后2的平方通过map和filter操作...
    println(listOf(1, 2, 3, 4).asSequence().map { it * it }.find { it > 3 })
    //先把1,2,3,4全部变成平方数，然后再find 大于3的
    println(listOf(1, 2, 3, 4).map { it * it }.find { it > 3 })

    //@TODO 在使用asSequence的时候，后面使用操作符的顺序也会对效率产生影响
    println(people.asSequence().filter { it.name.length > 5 }.map { it.name }.toList())
    println(people.asSequence().map { it.name }.filter { it.length > 5 }.toList())


    val naturalNumbers = generateSequence(0) { it + 1 }
    val numbersTo100 = naturalNumbers.takeWhile { it <= 100 }
    println(numbersTo100.sum())

    fun File.isInsideHiddenDirectory() = generateSequence(this) { it.parentFile }.any { it.isHidden }
    val file = File("D:\\mykeystore.jks")
    println(file.isInsideHiddenDirectory())

    //Passing a lambda as a parameter to a Java method
    //编译的时候会创建一个匿名内部类，和Java创传递参数一样，不同的是，如果匿名内部类的方法没有使用外面方法的变量，
    // 则该匿名内部类的实例只有一个，不管方法调用多少次，而Java是调用几次该方法就会创建几个匿名内部类实例
    val javaMethod = JavaMethod()
    javaMethod.postponeComputation(100) {
        println("invoke java method with lambda parameter")
    }


}


//example 1 写一个例子内部类实例创建一次，不管handleComputation方法调用多少次
val runnable = Runnable {
    println()
}

fun handleComputation() {
    val javaMethod = JavaMethod()
    javaMethod.postponeComputation(100, runnable)
}

//example 2
fun handleComputation(id: Int) {
    val javaMethod = JavaMethod()
    javaMethod.postponeComputation(100) {
        println(id) //使用了外面的方法handleComputation的id变量，则匿名内部类实例不会重用，每调用handleComputation一次创建一次
    }
}


