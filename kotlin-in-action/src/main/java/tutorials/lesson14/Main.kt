package tutorials.lesson14

import base.Person

/*
 * 
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */

const val DD = "ddd"
const val COUNT = 100

val dd:Person = Person("kumushuoshuo")


fun main() {
    val propertyTest = PropertyTest()
    //propertyTest.name
    propertyTest.name = "kumushuoshuo"

    propertyTest.setGender(1)

    //val test = LateinitTest()
    //test.checkInit()
}

