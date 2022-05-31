package tutorials.lesson18

/*
 * 
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */

enum class Country {
    CN,
    USA
}

fun printCountryWithEnum(country: Country) {
    when (country) {
        Country.CN -> println("中国")
        Country.USA -> println("美国")
    }
}

fun main() {
    printCountryWithEnum(Country.CN)
}