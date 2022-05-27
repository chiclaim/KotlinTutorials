package tutorials.lesson14

class LateinitTest{
    lateinit var name:String

    fun checkInit(){
        println(::name.isInitialized)
        name = "kumushuoshuo"
        println(::name.isInitialized)
    }
}