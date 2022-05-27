package tutorials.lesson14

/**
 *
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */
class PropertyTest {

    var name:String? = "苦木说说"
        get() {
            println("invoke get()")
            return field
        }
        set(value) {
            println("invoke set()")
            field = value
        }

    var salary = 0
        private set

    var age = 0
        // https://youtrack.jetbrains.com/issue/KT-3110
        // private get //error

    private var gender = 1
    fun setGender(sex:Int){
        gender = sex
    }

    var isEmpty: Boolean = true
        private set
        get(){
            field = false
            return false
        }

}