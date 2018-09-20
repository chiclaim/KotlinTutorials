package base

import new_class.Person
import new_class.Person7

/**
 * desc: 调用当前module中internal class
 *
 * Created by Chiclaim on 2018/09/20
 */

fun getInternalClassFromThisModule() {

    //Person7 is a internal class
    val p2 = Person7()

}