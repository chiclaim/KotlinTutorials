package delegated

/**
 * Desc: Kotlin 简化委托模式 演示
 * Created by Chiclaim on 2018/9/20.
 */

interface IAnimal {
    fun eat()
    fun run()
}

class Kangaroo : IAnimal {

    override fun eat() {
        println("eating grass ...")
    }

    override fun run() {
        println("jump...")
    }

}

class KangarooDelegate(private val kangaroo: Kangaroo = Kangaroo()) : IAnimal{
    override fun eat() {
        kangaroo.eat()
    }

    override fun run() {
        kangaroo.run()
    }

}

//注意：如果使用val/var修饰了构造方法参数，记得要设置private，否则就暴露了被代理的对象了
class KangarooDelegate2(private val kangaroo: Kangaroo = Kangaroo()) : IAnimal by kangaroo {

    //假如 需要对run方法进行增强改造
//    override fun run() {
//        kangaroo.run()
//        println("shout...")
//    }

    //kotlin会替我们生成其他代理方法，如 eat 方法
    /*
       public void eat() {
          this.kangaroo.eat();
       }
     */
}

fun main(args: Array<String>) {
    val k = KangarooDelegate2()
    k.run()
}

/*
class UserRepository constructor(val userSource: IUserSource) : IUserSource{

    override fun login(telephone:String, code:String){
        userSource.login(telephone,code)
    }

    override fun register(telephone:String, code:String){
        userSource.register(telephone,code)
    }

    ...//省略其他业务
}

// 简化为：

class UserRepository constructor(userSource: IUserSource) : IUserSource by userSource
 */


