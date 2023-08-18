package inline

import kotlin.math.max

/**
 * inline、noinline、crossinline
 *
 * 1. inline 基本原理
 * 2. 调用 inline 高阶函数，传入的 lambda 体使用外部变量和不使用外部变量的情况
 * 3. noinline 基本原理
 * 4. noinline 的作用
 * 5. inline 与 return
 * 6. crossinline
 */
class InlineTest {

    var age = 18

    // 在普通方法中添加 inline 没有意义
    // Expected performance impact from inlining is insignificant. Inlining works best for functions with parameters of functional types
    // inline 关键字主要用于高阶函数
    inline fun inlineFun() {
        println("from inlineFun")
        max(1, 2)
    }

    private fun proxy(action: () -> Unit) {
        println("start logging")
        action.invoke()
        println("end logging")
    }

    fun invokeProxy() {
        proxy {
            //age = 11
            println("eating")
        }
    }

    // 高阶函数也不能无脑加 inline，同时要考虑函数大小问题，尽可能让inline函数代码行数变小
    private inline fun proxyInline(action: () -> Unit) {
        println("start logging")
        action.invoke()
        println("end logging")
    }

    fun invokeProxyInline() {
        proxyInline {
            println("eating")
        }
    }

    /**
     * action 进行内联，action2 内联
     */
    private inline fun proxyInline2(action: () -> Unit, noinline action2: () -> Unit) {
        println("start logging")
        action.invoke()
        println("end logging")
        action2.invoke()
    }

    fun invokeProxyInline2() {
        proxyInline2({
            println("eating...")
        }, {
            println("eating...2")
        })
    }

    // 那 oninline 有啥用，我们将 proxyInline2 改一改
    // 如果把 action2 的 online 去掉则报错，不能将内联的 action2 作为参数传递给高阶函数
    // 很好理解，因为如果 action2 被内联了，编译后，就变成了代码片段块了，目前只能使用(内部)类来表示代码块，所以 action2 不能被内联
    private inline fun proxyInline3(action: () -> Unit, noinline action2: () -> Unit) {
        println("start logging")
        action.invoke()
        println("end logging")
        cleanResource(action2)
    }

    private fun cleanResource(execution: () -> Unit) {
        execution.invoke()
        println("cleaning resource1")
        println("cleaning resource2")
    }

    fun invokeProxyInline3() {
        proxyInline2({
            println("eating...")
        }, {
            println("eating...2")
        })
    }


    private inline fun proxyInline4(action: () -> Unit) {
        println("start logging")
        action.invoke()
        println("end logging")
    }


    fun invokeProxyInline4() {
        proxyInline4 {
            println("eating...")
            // 在 lambda 中使用 return，该函数必须是 inline
            return
        }
    }


    private fun wrap(action: () -> Unit) {
        action.invoke()
    }

    private inline fun proxyInline5(action: () -> Unit) {
        println("start logging")
        wrap {
            // Can't inline 'action' here: it may contain non-local returns. Add 'crossinline' modifier to parameter declaration 'action'
            // action.invoke()
        }
        println("end logging")
    }

    private inline fun proxyInline6(crossinline action: () -> Unit) {
        println("start logging")
        wrap {
            action()
        }
        println("end logging")
    }


    fun invokeProxyInline6() {
        proxyInline6 {
            println("eating...")
            // 使用 crossinline 修饰的 lambda 不允许使用 return
            // return
        }
    }

}

fun main() {
//    InlineTest().invokeProxyInline4()
    InlineTest().invokeProxyInline6()
}









