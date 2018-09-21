package collection

/**
 * Created by Chiclaim on 2018/7/31.
 */

fun main(args: Array<String>) {
    //example 1
    val people = listOf(Person("Zhiclaim", 11), Person("Johnny", 23))
    println("it.name --> ${people.maxBy { it.name }}")

    //example 2
    val getMaxAge = { p: Person -> p.age }
    println("p: Person -> p.age ${people.maxBy(getMaxAge)}")
}