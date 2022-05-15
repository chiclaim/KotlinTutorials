## 1、空安全

Kotlin 中包含可空类型（Nullable types）和不可空类型（Non-Null Types），任何复杂类型都包括可空类型和不可空类型，例如下面的 String 类型：

```kotlin
fun main() {
    var name: String? // 可空类型
    var name2: String // 不可空类型
}
```

类型后面加上`?` 则表示可空类型，否则是不可空类型。对于不可空类型不能将 `null` 赋值给它。

```kotlin
fun main() {
    var name: String? // 可空类型
    var name2: String // 不可空类型

    name = null  // 合法
    name2 = null // 编译器报错
}
```

所以，如果一个函数的参数是不可空类型，那么不能将可空类型作为参数进行传递。

```kotlin
fun main() {
    var name2: String? // 可空类型
    test(name2) // 编译报错
}

fun test(str: String) {
    //...
}
```

集合的元素如果声明为不可空，那么不能将可空的元素添加到集合中：

```kotlin
val intList: MutableList<Int> = mutableListOf()
var intValue: Int? = null
intList.add(intValue)
```

将元素可空的集合转成元素不可空的集合：

```kotlin
val nullableList: List<Int?> = listOf(1, 2, null, 4)
val intList: List<Int> = nullableList.filterNotNull()
```


Kotlin 的类型体系（后面会详细介绍）目标就是消除危险的空引用。像 Java 中如果一个空引用调用方法就会抛出空指针异常。

那么 Kotlin 是如何保证空安全的呢？

### 安全调用

对于一个空类型的变量，调用其属性或者方法时，可以使用空安全调用：`?.`， 例如：

```kotlin
fun main() {
    var name: String? = null // 可空类型

    //val len = name.length // 编译报错
    val len = name?.length
}
```

当 `name = null` 时，`name?.length` 返回的就是 null，和下面的 Java 代码片段等价：

```
Integer len = null;
if(name != null){
	len = name.length();
}
```

可见 `?.` 操作符相对于 Java 来说帮我们减少了大量的代码。

**需要注意的时候，通过安全调用操作符`?.` 返回的类型也是可空类型。**

### Elvis 操作符

对于可空类型，还可以使用 Elvis 操作符 `?:`，大家不要跟 Java 里面的三目运算符搞混淆了。

```kotlin
var name: String? = null // 可空类型

fun main() {
    val result = name ?: return
    println(result)
}
```

上面的代码的意思是，当 name = null，那么直接 return ，否则将 name 的值赋给 result 变量。

我们再来看一个例子：

```kotlin
val len: Int = if (b != null) b.length else -1
```

上面代码逻辑为获取 b 的长度，如果 b = null 则 len = -1，可以使用 `?:` 进行改造：

```kotlin
val len = b?.length ?: -1
```

### 安全强转

在 Java 中被强转的对象不是目标类型，强制转换会抛出 ClassCastException，在 Kotlin 中可以使用 `as?` 进行安全转换：

```kotlin
val str: String? = value as? String
```

如果强制转换失败，则 `str = null`

### 非空断言

非空断言运算符：`!!`，对一个可空类型的变量上使用非空断言，就是像编译器保证该变量不为空，当运行时发现该变量为 null 时，则抛出 `NullPointException`

```kotlin
val len = b!!.length
```

尽可能的不要使用非空断言，因为它可能导致空指针。

## 2、实战中的编程小技巧

### 技巧1

很多初学者经常会有这样的疑问，为什么我对一个引用进行判空处理，然后我使用该引用变量的时候，编译器还是报错，感觉 Kotlin 很难用，例如：

```kotlin
var str: String? = null

fun test() {
    if (str != null) {
        // 其他代码
        println(str)
        if (str.length > 10) { // 编译报错，但是我不想使用非空断言 !!
            todo
        }
    }
}
```

原因很简单， 因为 str 是可变的，虽然你在前面判断是否为空，但是在判断后，其他代码可能又设置为 null 了，所以编译器会报错。

你可以使用 let 函数来解决：

```kotlin
str?.let {
    println(str)
    if (it.length > 10) {
        todo
    }
}
```

如果不想用那么多大括号，你可以使用 `?:` 操作符：

```kotlin
val s = str ?: return
println(str)
if (s.length > 10) {
    todo
}
```

经过以上改造，你的 Kotlin 更加 Native

### 技巧2

我们在写代码的时候经常需要进行逻辑判断，那么就不可避免的和 bool 值打交道：

```kotlin
fun test() {
    if (str?.length > 10) { // 编译器报错，因为 str?.length 可能为 null
        println(str)
    }
}
```

可以改成如下形势：

```kotlin
fun test() {
    if ((str?.length ?: -1) > 10) {
        println(str)
    }
}

```

当属性值是 boolean 值时：

```kotlin
fun test() {
    if (user?.isAdult) { // 编译器报错
        //...
    }
}
```

可以改造成：

```kotlin
fun test() {
    if (user?.isAdult == true) {
        //...
    }
}
```
