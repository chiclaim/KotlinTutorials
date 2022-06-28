package reflection

import kotlin.reflect.full.*


fun main() {
    val book = Book(1,"Kotin入门到进阶", "kumushuoshuo")
    val kClass = book::class
    println("simpleName = ${kClass.simpleName}")
    println("打印属性--------")

    // 打印成员属性（包括父类中的）
    kClass.memberProperties.forEach {
        println("memberProperties=${it.name}")
    }
    // 打印扩展属性（包括父类中的）
    kClass.memberExtensionProperties.forEach {
        println("memberExtensionProperties=${it.name}")
    }
    // 打印成员属性（不包括父类中的）
    kClass.declaredMemberProperties.forEach {
        println("declaredMemberProperties=${it.name}")
    }
    // 打印扩展属性（不包括父类中的）
    kClass.declaredMemberExtensionProperties.forEach {
        println("declaredMemberExtensionProperties=${it.name}")
    }


    println("打印函数--------")

    // 打印所有的非扩展函数（包括父类中的）
    kClass.memberFunctions.forEach {
        println("memberFunctions=${it.name}")
    }
    // 打印所有的扩展函数（包括父类中的）
    kClass.memberExtensionFunctions.forEach {
        println("memberExtensionFunctions=${it.name}")
    }
    // 打印所有的非扩展函数（不包括父类中的）
    kClass.declaredMemberFunctions.forEach {
        println("declaredMemberFunctions=${it.name}")
    }
    // 打印所有的扩展函数（不包括父类中的）
    kClass.declaredMemberExtensionFunctions.forEach {
        println("declaredMemberExtensionFunctions=${it.name}")
    }

}