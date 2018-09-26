package nullability

/**
 * Desc:
 * Created by Chiclaim on 2018/9/18.
 */
class LateInitTest {
    private lateinit var email: String
    fun main(name: String?) {
        name?.let {
            email = ""
        }
        email?.let {
            System.out.println("---")
        }

        name.isNullOrBlank()

    }
}



