package higher_order_function

import java.util.concurrent.locks.Lock

/**
 * Desc: 高阶内联函数
 * Created by Chiclaim on 2018/10/8.
 */

//==============内联函数 starting==============================
//内联函数在被调用的时候直接直接把方法体的逻辑拷贝到调用者里
//需要注意两点：
// 1，内联函数的参数lambda不能用遍历保存；
// 2，内联函数的参数lambda只能传递给内联函数
//否则编译器会提示：Illegal usage of inline-parameter
inline fun <T> synchronized(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        //return wrap(action)
        return action()
    } finally {
        lock.unlock()
    }
}

/*inline*/ fun <T> wrap(action: () -> T): T {
    return action()
}

fun test(lock: Lock) {
    println("Before sync")
    synchronized(lock) {
        println("Action")
    }
    println("After sync")
}
//==============ending===============================


//==============starting=============================
//下面是非内联函数的调用，下面是通过内部类来实现的
fun <T> synchronized2(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action()
    } finally {
        lock.unlock()
    }
}


fun test2(lock: Lock) {
    println("Before sync")
    synchronized2(lock) {
        println("Action")
    }
    println("After sync")
}
//==============ending=============================
