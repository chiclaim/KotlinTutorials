package tutorials.lesson32

// inline/noinline/crossinline
class Main {

    var age = 10

    inline fun inlineFun() {
        println("from inlineFun")
    }


    private inline fun proxy(action: () -> Unit) {
        println("start logging")
        action()
        println("end logging")
    }

    fun invokeProxy() {
        proxy {
            age = 18
            println("eating")
        }
    }

    private inline fun normalProxy(action: () -> Unit) {
        println("start logging")
        action()
        println("end logging")
    }

    fun test(age:Int) {
        if (age <= 0) {
            return
        }
        println(age)
    }

    fun invokeProxy2() {
        normalProxy {
            if(age<=0){
                return
            }
            println(age)
        }
    }

    // Non-local returns

    // noinline

    private fun cleanResource(execution: () -> Unit) {
        execution()
        println("cleaning resource1")
        println("cleaning resource2")
    }

    private inline fun proxy(action: () -> Unit, noinline action2: () -> Unit) {
        println("start logging")
        action()
        println("end logging")
        cleanResource(action2)
    }

    fun invokeProxy3() {
        proxy({
            println("eating...")
        }, {
            println("eating...2")
        })
    }

    // crossinline

    private fun wrap(action: () -> Unit) {
        action.invoke()
    }

    private inline fun proxy2(noinline action: () -> Unit) {
        println("start logging")
        wrap {
            action()
        }
        println("end logging")
    }


    private fun invoke(){
        proxy2{
            println("-----")
        }
    }

}
