package delegated

import kotlin.reflect.KProperty

/**
 *
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */
class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

class Example {
    var p: String by Delegate()
}

fun main() {

    val e = Example()
    e.p = "kumushuoshuo"
    println(e.p)


}