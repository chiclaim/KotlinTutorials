package delegated

/**
 *
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */
class DelegatedPropertyByMap(val map: Map<String, Any>) {
    val name: String? by map
    val age: Int by map
}

fun main() {
    val delegate = DelegatedPropertyByMap(hashMapOf("age" to 12))
    println(delegate.age)
    //println(delegate.name)
}