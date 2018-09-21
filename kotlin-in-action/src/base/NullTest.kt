package base

/**
 * Desc:
 * Created by Chiclaim on 2018/9/18.
 */

fun test(name: String?) {
    System.out.println("---" + name.isNullOrBlank())
}

fun test2(age: String?): Int = age?.toInt() ?: 0




fun main(args: Array<String>) {
    test(null)

    System.out.println("---" + test2(null))
    System.out.println("---" + test2("99"))
}
