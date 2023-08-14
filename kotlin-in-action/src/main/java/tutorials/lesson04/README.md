
# 一、条件判断
## 1、when精讲
在 Java 中有 switch 语句，在 Kotlin 中使用 `when` 来代替 switch。同时 when 也可以代替 if 。你以为 when 只是用来代替 switch 和 if 的吗？其实远远不止这些，其中还包含了一些不为认知的小秘密。下面我们都会为大家一一揭晓。

### 1.1、when的基本语法

```kotlin
when(parameter){
    branch1 -> logic
    branch2 -> logic
}
```
when 括号里是参数，参数是可选的。箭头(->) 左边是条件分支，右边是对应的逻辑体

when 不需要向 switch 那样需要加上 break 语句，符合条件自动具有 `break` 功能

如果逻辑体代码比较多，可以放到花括号 `{}` 里:

```kotlin
when(parameter){
    branch1 -> {
        //...
    }
    branch1 -> {
        //...
    }
}
```

如果要组合多个分支，可以使用逗号(,)分隔分支：

```kotlin
when(parameter){
    branch1,branch1 -> {
        //...
    }
}
```

### 1.2、枚举类对象作为when参数

```kotlin
fun getMnemonic(color: Color) = when (color) {
     Color.RED -> "Richard" 
     Color.ORANGE -> "Of" 
     Color.YELLOW -> "York" 
     Color.GREEN -> "Gave" 
     Color.BLUE -> "Battle" 
     Color.INDIGO -> "In" 
     Color.VIOLET -> "Vain"
}
```



需要注意的是，when 使用枚举对象作为参数，需要把该枚举类的所有对象列举完

所以 枚举对象作为 when 参数不需要 else 分支

### 1.3、任意对象作为when参数

Kotlin 中的 when 比 Java 中的 switch 功能更强大

Java 的 switch 参数只能是 枚举常量、字符串、整型或整型的包装类型(浮点型不可以)

Kotlin 的 when 可以是任意对象：

```kotlin
fun mix(c1: Color, c2: Color) = when (setOf(c1, c2)) {
    
    setOf(RED, YELLOW) -> ORANGE 
    
    setOf(YELLOW, BLUE) -> GREEN 
    
    setOf(BLUE, VIOLET) -> INDIGO
    
    //需要处理 其他 情况
    else -> throw Exception("Dirty color") 
}
```


### 1.4、无参数的when表达式

上面的 mix 函数比较低效，因为每次比较的时候都会创建一个或多个 set 集合

如果该函数调用频繁，会创建很多临时对象

可以使用无参的 when 表达式来改造下：

```kotlin
fun mixOptimized(c1: Color, c2: Color) = when {
    (c1 == RED && c2 == YELLOW) || (c1 == YELLOW && c2 == RED) ->
        ORANGE
    (c1 == YELLOW && c2 == BLUE) || (c1 == BLUE && c2 == YELLOW) ->
        GREEN
    (c1 == BLUE && c2 == VIOLET) || (c1 == VIOLET && c2 == BLUE) ->
        INDIGO
    else -> throw Exception("Dirty color")
}
```

无参数的 when 表达式的条件分支必须是 **boolean** 类型

### 1.5、智能类型转换(smart-casts)

在 Java 中对某个对象进行类型转换的时候时候，需要通过 **instanceof** 来判断是否可以被强转

```kotlin
void test(Object obj) {
    if (obj instanceof String) {
        String str = (String) obj;
        str.substring(0, str.length() / 2);
    }
    //...
}

```

Kotlin 通过 **is** 关键字来判断类型，并且编译器会自动帮你做类型转换

```kotlin
fun test(obj: Any) {
    if (obj is String) {
        // 不需要手动做类型转换操作
        obj.substring(0, obj.length / 2)
    }
    //...
}
```

### 1.6、when原理分析

例如下面的程序：

```kotlin
fun testWhen(index: Int) {
    when (index) {
        0 -> {
            println("0")
        }
        1, 2 -> {
            println("1,2")
        }
    }
}
```
也就是说，当 index = 1 或者 2 都会执行 `println("1,2")`


我们对上面的例子进行反编译：

```java
public final void testWhen(int index) {
   String var2;
   boolean var3;
   switch(index) {
   case 0:
      var2 = "0";
      var3 = false;
      System.out.println(var2);
      break;
   case 1:
   case 2:
      var2 = "1,2";
      var3 = false;
      System.out.println(var2);
   }
}
```
发现，它底层还是通过 Java 的 switch 来实现的。我们对上面的 kotlin 案例进行小的修改：

```kotlin
fun testWhen(index: Int) {
    when (index) {
        0 -> {
            println("0")
        }
        1, 2 -> {
            println("1,2")
        }
        in 4..10 -> {
            println(4..10)
        }
    }
}
```
在反编译可以看出，发生了变化（变成了 if）：

```java
public final void testWhen(int index) {
   String var3;
   boolean var4;
   if (index == 0) {
      var3 = "0";
      var4 = false;
      System.out.println(var3);
   } else if (index != 1 && index != 2) {
      if (4 <= index) {
         if (10 >= index) {
            byte var5 = 4;
            IntRange var6 = new IntRange(var5, 10);
            var4 = false;
            System.out.println(var6);
         }
      }
   } else {
      var3 = "1,2";
      var4 = false;
      System.out.println(var3);
   }
}
```
当我们加上了 `in 4..10` 条件，那么底层则无法通过 switch 来实现了，所以只能转而使用了 if 来实现。

### 1.7、when fallthrough

在 Java switch 是支持 fallthrough 的：

```
    static void test(String value) {
        switch (value) {
            case "one":
                System.out.println("1");
            case "two":
                System.out.println("2");
                break;
            case "three":
                System.out.println(3);
                break;
        }
    }
```
test("one") 会输出 1,2

但在 Kotlin 中不支持 when 的 fallthrough，因为Java 中在使用 switch 的如果默认是 fallthrough ，需要显式的加上 break，这样容易产生bug，如上面的 Java 代码。

所以在Kotlin 中 when 是不支持 fallthrough 的。


### 1.8、when的程序健壮性
经过上面的分析我们知道，在 Kotlin 中使用 when 来代替 switch 或 if，除此以外，他们还有哪些不同吗？好，我们来看下下面的一个 Java switch 例子：

```java
public void test(String value){
    switch (value){
        case "hello":
            System.out.println("hello");
            break;
        case "world":
            System.out.println("world");
            break;
        default:
            System.out.println("unknown");
    }
}
```
Java 基础比较好的都知道，上面的代码有可能会抛出 NullPointException，当我们调用 test 方法的时候传递的参数为 null 时，就会抛出异常，因为在 Java 中对 String 进行 switch 本质上是使用了 string.hashCode 方法。 下面我们使用 Kotlin 来改写上的例子：

```kotlin
fun test(value: String?) {
    when (value) {
        "hello" -> println("hello")
        "world" -> println("world")
        else -> println("unknown")
    }
}
```
我们再来看下反编译的结果：

```java
public final void test(@Nullable String value) {
   String var3;
   boolean var4;
   if (value != null) {
      switch(value.hashCode()) {
      case 99162322:
         if (value.equals("hello")) {
            var3 = "hello";
            var4 = false;
            System.out.println(var3);
            return;
         }
         break;
      case 113318802:
         if (value.equals("world")) {
            var3 = "world";
            var4 = false;
            System.out.println(var3);
            return;
         }
      }
   }

   var3 = "unknown";
   var4 = false;
   System.out.println(var3);
}
```

从上面的反编译后的代码不难看出，在对 `switch case` 之前，进行 if 判空处理，所以就算 `test` 方法参数为 `null` 也不会出现空指针异常。
可以看出，相似的代码逻辑，使用 Kotlin 来实现要比 Java 来实现要健壮的多。其实这只是 Kotlin 优势的冰山一角，随着学习的深入，我想你会越来越喜欢 Kotlin。明白这些底层原理，我相信你对自己的 Kotlin 代码越来越自信，因为你知道你写的每行 Kotlin 代码在底层意味着什么。


### 1.9、when总结
至此，`when` 的介绍就要告一段落了。**Kotlin 使用 when 统一了条件判断**，当然经典的 if 也可以用作条件判断，但是在 Kotlin 中更多的是使用 when 来进行条件分支的判断，if 在 Kotlin 有它独有的应用的地方。同时 when 相对于 Java 中的 switch 来说，减少了很多程序员容易调入的陷阱，**增加了程序的稳定性**。除此以外，when 还有一些东西可以讲，这个我们留到介绍 `枚举` 的时候再来细聊，同时也会结合实际工作中的一些思考 ，来聊聊 **when 和 枚举** 结合使用的情况和问题，这个相对于只介绍语法来说更加重要。


## 2、if

if 表达式 用于条件判断，在 Kotlin 中 如果判断分支比较多，通常使用 **when** 来替代 **if**，如：

```kotlin
fun test(obj: Any) {
    when (obj) {
        is String -> obj.substring(0, obj.length / 2)
        is Type2 -> ignore
        is Type3 -> ignore
    }
}
```
if 还可以实现三目运算符：

```kotlin
fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}
```



# 二，循环
## 1、while，do...while
Kotlin 中的 **while** 和 **do...while** 循环和 Java 没有什么区别

```kotlin
while (condition) {
    /*...*/
}

do {
    /*...*/
} while (condition)
```

## 2、for循环
### `..`操作符

**for** 循环的语法和 Java 中的循环还是有些区别

```kotlin
// Java for 循环
for (int i = 0; i <= 100; i++) {
    System.out.println(i);
}

// 对应 Kotlin 版本
for(i in 0..100){
    println(i)
}
```

使用 `..` 操作符 表示一个区间，**该区间是闭区间，包含开始和结束的元素**。

### in、until操作符

然后使用 **in** 操作符来遍历这个区间，这个区间是从小到大的，如果开始的数字比结尾的还要大，则没有意义

如果想要表示 **半闭区间** ，即 **只包含头部元素，不包含尾部**，可以使用 **until** 操作符：

```kotlin
for(i in 0 until 100){
    println(i)
}
```

### in、downTo操作符
如果想要**倒序遍历**，可以使用 **downTo** 关键字：

```kotlin
for(i in 100 downTo 0){
    println(i)
}
```
反编译后：

```java
int i = 100;
for(boolean var3 = false; i >= 0; --i) {
   boolean var4 = false;
   System.out.println(i);
}

// 其实就相当于递减：
for(int i = 100; i >= 0; --i) {
    System.out.println(i);
}

// 输出 100 到 0 之间的数
```

遍历的时候 **步长(step)** 默认是 **1**，可以通过 **step** 关键字来指定步长

```kotlin
for( i in 100 downTo 0 step 2){
    println(i)
}
```

操作符 `..` 和 `downTo` 表示区间都是闭区间，包含首尾元素的。



# 三、小结
至此，我们就介绍完了 Kotlin 中的 when、if、loop，其中话了大量的篇幅介绍了 when 的用法及背后原理，同时也介绍了 when 相对于 Java switch 在程序健壮性上更具优势。由于 while 和 do...while, for 循环和其他语言类似，就没有做大篇幅的赘述。
