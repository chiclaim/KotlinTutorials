/**
 * Created by dh on 2017/7/4.
 */


class LengthCounter {
    var counter: Int = 0
        private set

    fun addWord(word: String) {
        counter += word.length
    }

}

fun main(args: Array<String>) {
    val lengthCounter = LengthCounter()
    lengthCounter.addWord("Hello world")
    println(lengthCounter.counter)
}