package new_class

/**
 * Desc: 属性的 getter和setter方法 演示
 * Created by Chiclaim on 2018/9/19.
 */
class Person3 {
    //可变属性
    var name: String? = null
    //不可变
    val age: Int = 0
}

/*
public final class Person3 {
   @Nullable
   private String name;
   private final int age;

   @Nullable
   public final String getName() {
      return this.name;
   }

   public final void setName(@Nullable String var1) {
      this.name = var1;
   }

   public final int getAge() {
      return this.age;
   }
}

 */