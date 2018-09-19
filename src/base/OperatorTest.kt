package base

/**
 * desc: Kotlin 操作符 演示
 *
 * Created by Chiclaim on 2018/09/19
 */

class Person(var name: String)

fun getPerson(): Person? = null

// == 操作符 相当于Java里的equals()方法
fun equalsTest() {
    val p1 = Person("chiclaim")
    val p2 = getPerson()

    println(p2 == p1)

    /*
        ==  底层调用 Intrinsics.areEqual(p2, p1); 方法
        虽然 kotlin == 相当于 java equals，kotlin避免了空指针异常

        public static boolean areEqual(Object first, Object second) {
            return first == null ? second == null : first.equals(second);
        }
     */
}

fun main(args: Array<String>) {


}