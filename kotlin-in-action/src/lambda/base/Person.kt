package lambda.base

data class Person(var name: String, var age: Int) {
    lateinit var children: List<Person>
}
