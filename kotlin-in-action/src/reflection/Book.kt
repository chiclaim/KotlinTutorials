package reflection

import reflection.framework.Dao
import reflection.framework.annotations.Field
import reflection.framework.annotations.ID
import reflection.framework.annotations.Table

@Table("t_book")
class Book(@ID("id") val id: Int,
           @Field("name") val name: String,
           @Field("author") var author: String): Dao<Book>() {

    private var price = 119.0

    fun present(age: Int) = "book's name = $name,  author = $author age = $age"

    companion object {
        fun sayHello() {
            println("hello")
        }
    }

    inner class Chapter(private val title: String) {
        override fun toString(): String {
            return "book_name=$name, chapter_title=$title"
        }
    }
}