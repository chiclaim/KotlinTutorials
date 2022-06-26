package reflection

import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty1


class Book(val name: String, var author: String) {

    private var price = 119.0

    fun present(age:Int) = "book's name = $name,  author = $author age = $age"

    companion object{
        fun sayHello(){
            println("hello")
        }
    }

    inner class Chapter(private val title:String){
        override fun toString(): String {
            return "book_name=$name, chapter_title=$title"
        }
    }
}

fun <T> printProperty(instance: T, prop: KProperty1<T, *>) {
    println("${prop.name} = ${prop.get(instance)}")
}

fun <T> changeProperty(instance: T, prop: KMutableProperty1<T, String>) {
    val value = prop.get(instance)
    prop.set(instance, "$value, Johnny")
}

fun main() {

    // 1. class references (KClass)
    val klazz = String::class //KClass

    val clazz = String::class.java  //Java Class
    // 如果无法引用到类名，则可以通过 Java 中 Class.forName 获取 Class，然后转成 KClass
    val klass = Class.forName("java.lang.String").kotlin

    var book = Book("Kotlin从入门到放弃", "Chiclaim")

    // 2. bound class references (KClass)
    val clz = book::class

    // 3. constructor references
    val cr = ::Book
    book = cr("Kotlin从入门到放弃", "Chiclaim")

    // 4. bound constructor references
    val bcr = book::Chapter //Chapter 是 Book 的内部类，使用外部类的实例创建 bound constructor references
    val chapter = bcr("前言")
    println(chapter)

    // 5. property references (KProperty)
    // 获取属性的值
    println(Book::name.get(book))
    // 修改属性的值
    Book::author.set(book,"by chiclaim")

    // 6. bound property references (KProperty)
    // 获取属性的值
    println(book::name.get())
    // 修改属性的值
    book::author.set("by kumushuoshuo")

    // 7. function references (KFunction)
    val fr = Book::present
    println("function name is ${fr.name}")
    println(fr.invoke(book,12))
    println(fr(book,12))

    // 8. bound function references (KFunction)
    val bfr = book::present
    bfr(18)

}


/*


Book::name 会生成实现了 KProperty 接口的内部类

val prop = Book::name

KProperty1 prop = MemberReferenceTestKt$main$prop$1.INSTANCE;

final class MemberReferenceTestKt$main$prop$1 extends PropertyReference1 {
   public static final KProperty1 INSTANCE = new MemberReferenceTestKt$main$prop$1();

   public String getName() {
      return "name";
   }

   public String getSignature() {
      return "getName()Ljava/lang/String;";
   }

   public KDeclarationContainer getOwner() {
      return Reflection.getOrCreateKotlinClass(Book.class);
   }

   @Nullable
   public Object get(@Nullable Object receiver) {
      return ((Book)receiver).getName();
   }
}
 */