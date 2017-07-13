package collection


/**
 * Created by dh on 2017/7/10.
 */

data class Person(var name: String, var age: Int)

fun main(args: Array<String>) {
    //example 1
    val people = listOf(Person("Zhiclaim", 11), Person("Johnny", 23))
    println("it.name --> ${people.maxBy { it.name }}")

    //example 2
    val getMaxAge = { p: Person -> p.age }
    println("p: Person -> p.age ${people.maxBy(getMaxAge)}")


    //example 3
    val sum = { x: Int, y: Int ->
        println("Computing the sum of $x and $y")
        x + y
    }
    println(sum(2, 3))

    //example 4  member reference
    val getMaxAge2 = Person::age
    println("Person::age --> ${people.maxBy(getMaxAge2)}")


    //example 5 function reference
    fun salute() = println("function reference Salute")
    //salute()
    run(::salute)

    //example 6 constructor reference(You can store or postpone the action of creating an instance of a class using a constructor reference)
    val createPerson = ::Person
    val p = createPerson("Chiclaim", 29)
    println(p)

    //example 7 bound references
    val person = Person("Johnny", 24)
    val personsAgeFunction = person::age
    println("bound reference1 ${personsAgeFunction()}")

    val personsAgeFunction2 = Person::age
    println("bound reference2 ${personsAgeFunction2(person)}")

}




