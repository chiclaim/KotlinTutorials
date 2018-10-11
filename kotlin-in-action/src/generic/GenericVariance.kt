package generic

/**
 * Desc:
 * Created by Chiclaim on 2018/10/10.
 */


fun testGenericNumber(numbers: List<Number>) {
}


fun testGenericNumber2(numbers: MutableList<Number>) {
}


fun main(args: Array<String>) {
    val ints: List<Int> = listOf(1, 3, 4)
    //因为Int继承自Number
    //所以List<Int>是List<Number>的子类型
    //像这样的类或接口如List，称之为协变(covariant)类(接口)
    testGenericNumber(ints)

    //-------------------------------------


    // 根据上面的介绍发现List是协变类
    // 我们在来看下MutableList是否是协变类
    val ints2: MutableList<Int> = mutableListOf(1, 3, 4)
    //并不能成功传递参数，所以MutableList并不是一个协变类(invariant)
    //testGenericNumber2(ints2)

    //-------------------------------------

    //我们分别来看下协变类List和非协变类MutableList的源码声明
    //public interface List<out E>
    //public interface MutableList<E> : List<E>


}

fun main(value:Int){

}

