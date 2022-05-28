package delegated

/**
 *
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */
class DelegatedPropertyByMap(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int by map
}