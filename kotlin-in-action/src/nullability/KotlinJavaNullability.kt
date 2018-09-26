package nullability

/**
 * Desc: Kotlin和Java交互关于Null相关的问题
 * Created by Chiclaim on 2018/9/26.
 */

class UserActivity : UserView {
    var userPresenter = UserPresenter(this)


    override fun showFriendList(list: List<User>) {
        list.forEachIndexed { index, user ->
            println("${index + 1}, $user")
        }
    }


    fun getFriendList() {
        println("-----------get remote friends")
        userPresenter.getRemoteFriendList()
    }

    fun getFriendList2() {
        println("-----------get local friends")
        userPresenter.getLocalFriendList()
    }

}


fun main(args: Array<String>) {
    with(UserActivity()) {
        getFriendList()
        getFriendList2() //It will throw a Exception
    }
}


