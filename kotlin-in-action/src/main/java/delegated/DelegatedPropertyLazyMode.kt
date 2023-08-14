package delegated

import tutorials.lesson10.println
import kotlin.concurrent.thread

class DelegatedProperty2 {
    val friends by lazy(LazyThreadSafetyMode.PUBLICATION) {
        println("init ------${Thread.currentThread().name}")
        loadFriendList()
    }

    private fun loadFriendList(): String {
        Thread.sleep(10)
        return "kumushuoshsuo${Thread.currentThread().name}"
    }
}

fun main() {
    val d = DelegatedProperty2()
    repeat(30){
        thread(name = "My thread${it}") {
            d.friends.println()
        }
    }

    Thread.sleep(5000)

}