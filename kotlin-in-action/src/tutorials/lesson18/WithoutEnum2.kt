package tutorials.lesson18

/**
 *
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */
class Country2 private constructor() {

    companion object {
        val CN = Country2()
        val USA = Country2()
    }

}

fun printCountryWithClass(country: Country2) {
    when (country) {
        Country2.CN -> println("中国")
        Country2.USA -> println("美国")
    }
}

fun main() {
    printCountryWithClass(Country2.CN)
}