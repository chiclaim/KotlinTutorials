package test

import new_class.Person

/**
 * desc: 演示调用两一个模块 internal修饰的类
 *
 * Created by Chiclaim on 2018/09/20
 */

fun getInternalClassFromOtherModule() {
    val p1 = Person("yuzhiqiang")

    //can't resolve person7，It's internal class
    //val p2 = Person7()

}