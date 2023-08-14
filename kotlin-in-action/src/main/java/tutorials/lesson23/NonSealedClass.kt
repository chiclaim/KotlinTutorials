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

/*
如果我们打成 jar 给其他地方使用，如果外部继承了 Gender，然后将新的类型传递给 testWhenClass2，
那么我们封装的类就会有问题，因为一开始我们以为只会有 Male、Female
 */
fun testWhenClass2(gender: Gender) {
    when (gender) {
        is Gender.Male -> println("Male")
        else -> println("Female")
    }
}

fun main() {
    testWhenClass(Gender.Male())
}
