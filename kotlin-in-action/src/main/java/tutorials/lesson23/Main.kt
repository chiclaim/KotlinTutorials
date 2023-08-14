package tutorials.lesson23

import tutorials.lesson23.PosTypeEnum.*

/**
 * Kotlin 1.6 when 在使用 枚举、Sealed class 的变化
 *
 *
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */

sealed class PosType private constructor(){
    object ABC : PosType()
    class ICBC : PosType()
}

// 如果 sealed class 的子类不需要保存数据，或者数据是没有状态的，则可以使用单例，此时和枚举非常像
/*
sealed class PosType {
    object ABC : PosType()
    object ICBC : PosType()
}
 */

fun testWhenExpressionSealedClass(posType: PosType) {
    val value:Int = when (posType) {
        is PosType.ABC -> 1
        is PosType.ICBC -> 2
    }
    println(value)
}

//fun testWhenStatementSealedClass(posType: PosType) {
//    // 编译报错：'when' expression must be exhaustive, add necessary 'is ICBC' branch or 'else' branch instead
//    when (posType) {
//        is PosType.ABC -> 1 // kotlin 1.7 会编译报错
//
//    }
//}


enum class PosTypeEnum {
    ABC, ICBC
}

fun testWhenExpressionEnum(posType: PosTypeEnum) {
    val value = when (posType) {
        ABC -> 1
        ICBC -> 2
    }
}

//fun testWhenStatementEnum(b: PosTypeEnum) {
//    // 编译报错：'when' expression must be exhaustive, add necessary 'is ICBC' branch or 'else' branch instead
//    // kotlin 1.7 会编译报错
//    when (b) {
//        ABC -> 1
//    }
//}
