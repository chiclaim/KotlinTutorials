package tutorials.lesson23


abstract class Gender {
    class Male : Gender()
    class Female : Gender()
}

class DoubleSex : Gender()

fun testWhenClass(gender: Gender) {
    when (gender) {
        is Gender.Male -> println("Male")
        is Gender.Female -> println("Female")

    }
}

fun main() {
    testWhenClass(Gender.Male())
}
