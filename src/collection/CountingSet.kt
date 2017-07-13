package collection

/**
 * Created by dh on 2017/7/10.
 */
class CountingSet<T>(val innerSet: MutableCollection<T> = HashSet<T>()) : MutableCollection<T> by innerSet {
    var objectsAdded = 0
    override fun add(element: T): Boolean {
        objectsAdded++
        return innerSet.add(element)
    }


    override fun addAll(elements: Collection<T>): Boolean {
        objectsAdded += elements.size
        return innerSet.addAll(elements)
    }
}

fun main(args: Array<String>) {
    val set = CountingSet<Int>()
    set.addAll(listOf(1, 2, 3))
    println("${set.objectsAdded} objects were added, ${set.size} remain")

}