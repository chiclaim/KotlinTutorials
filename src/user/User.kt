package user

/**
 * Created by dh on 2017/7/5.
 */

fun getFacebookName(accountId: Int): String {
    return "facebook$accountId"
}

class User(val nickname: String) {

    val username: String = "name"

    class InnerClass {
        fun print() {
            print("ddd")
        }
    }

    inner class InnerClass2{
        fun print() {
            print(username)
        }
    }



    companion object {
        fun newSubscribingUser(email: String) = User(email.substringBefore('@'))

        fun newFacebookUser(accountId: Int) = User(getFacebookName(accountId))
    }


}

fun main(args: Array<String>) {
    val facebookUser = User.newFacebookUser(4)
    println(facebookUser.nickname)
    val subscribingUser = User.newSubscribingUser("chiclaim@gmail.com")
    println(subscribingUser.nickname)
    val user = User("faf")
    println(user.nickname)

    User.InnerClass().print()
    user.InnerClass2().print()
}