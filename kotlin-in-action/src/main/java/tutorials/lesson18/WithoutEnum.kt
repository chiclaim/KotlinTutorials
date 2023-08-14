package tutorials.lesson18

/**
 *
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */
const val CN = 1
const val USA = 2

fun printCountryWithInt(country: Int) {
    when (country) {
        CN -> println("中国")
        USA -> println("美国")
    }
}

fun main() {
    printCountryWithInt(CN)
    printCountryWithInt(99)

}