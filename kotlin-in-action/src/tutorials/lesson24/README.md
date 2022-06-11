主要内容

1. 算术操作符重载
    1. 二元操作符重载
    2. 复合赋值操作符重载
    3. 一元操作符重载
2. 比较操作符重载
3. 集合相关的操作符重载
    1. 索引操作符重载
    2. 索引操作符重载Kotlin集合中的应用
    3. in 操作符重载
4. 区间操作符重载
5. 解构操作符重载
6. 属性委托
7. 总结

## 概述

Kotlin 允许开发者为自定义的类型，提供预定义的一组操作符的实现。这些预定义的操作符如 `加、减、乘、除` 等，它们具有固定的符号表示和固定的优先级

举个简单的例子：

```java
int a = 2, b = 4;
int d = a + b;
```
在这个简单的例子中，定义了2个 `int` 变量，这个 `+` 就是一个操作符。由于 int 是 `java` 语言内置的原始数据类型，天然具备了这些预定义操作符( `+、-、*、/` 等)的功能

但是实际的开发过程中，有很多类型是开发者自定义的，并不是语言内置的。如何为自定义的类型也实现相关的操作符，Kotlin 为特定的操作符提供了一个与之相应的名字的成员函数或扩展函数。

我们可以把这个函数称之为：重载操作符的函数。该函数需要使用关键字 `operator` 来修饰符。

说白了就是一个操作符对应一个固定的函数。下面我们就来看下 Kotlin 为我们提供了那些操作符重载


## 算术操作符重载

上面我们举的简单的例子中 `+`  就是一个算数操作符。

在算术操作符重载中我们要介绍是3个部分：

- 二元操作符重载
- 复合赋值操作符重载
- 一元操作符重载

### 二元操作符重载

何谓二元操作符（`binary arithmetic operations`）？二元操作符就是我们常说的 `加、减、乘、除、取模`

二元操作符 顾名思义就是在操作符左右两边都有被操作符数据，例如：`a + b`

我们上面说到，操作符背后对应的是一个函数，下面我们来看下二元操作符对应的函数名称：

操作符 | 函数名
---    |---
+      | plus
-    | minus
*    | times
/    |  div
%    | rem

我们使用 `Kotlin in Action` 中的 `Point` 类来介绍下二元操作符怎么用的

```kotlin
class Point(var x: Int, var y: Int) {
    //Potin有两个成员变量x、y

    //加法(+)
    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }
    
    //减法(-)
    operator fun dec(): Point {
        return Point(x - 1, y - 1)
    }

    //乘法(*)
    operator fun times(other: Point): Point {
        return Point(x * other.x, y * other.y)
    }

    //除法(/)
    operator fun div(other: Point): Point {
        return Point(x / other.x, y / other.y)
    }

    //取模(%)
    operator fun rem(other: Point): Point {
        return Point(x % other.x, y % other.y)
    }
    
    override fun toString(): String {
        return "Point[$x, $y]"
    }
}
```
重载操作符的方式有两种：要么在定义类的时候进行操作符重载，要么通过扩展函数的方式来进行操作符重载

下面来测试下二元操作符：

```Kotlin
fun main(args: Array<String>) {
    val p1 = Point(10, 30)
    val p2 = Point(20, 30)
    println("plus: ${p1 + p2}")
    println("minus: ${p1 - p2}")
    println("times: ${p1 * p2}")
    println("div: ${p1 / p2}")
}

//输出结果：
plus: Point[30, 60]
minus: Point[-10, 0]
times: Point[200, 900]
div: Point[0, 1]
```

除了 `加、减、乘、除、取模`，二元算数操作符其实还包括一些 `按位运算操作符`

需要注意的是 `Kotlin` 并没有定义类似 `Java` 中的按位运算符，Kotlin 通过定义了相关方法来实现对应的按位操作符

下面是 Kotlin 和 Java 的按位操作符对应表：

按位操作符名称 | Java | Kotlin
---|---|---
按位与|a & b|a.and(b)
按位或| a\|b | a.or(b)
按位异或 | a ^ b | a.xor(b)
按位非 | ~a | a.inv()
左移| a << b | a.shl(b)
有符号右移| a >> b | a.shr(b)
无符号右移| a >>> b | a.ushr(b)


### 复合赋值操作符重载

介绍完二元操作符，我们再来看下复合赋值操作符(`compound assignment operators`)

何谓复合操作符？诸如 +=、-=、*=、/= 都是复合赋值操作符, 例如：a += b 相当于 a = a + b

复合操作符和函数名对应表：

操作符 | 函数名
---|---
+= | plusAssign
-= | minusAssign
*= | timesAssign
/= | divAssign
%= | remAssign

我们还是以 `Point` 类作为例子，这次我们通过扩展函数的方式来实现操作符重载：

```Kotlin
// +=
operator fun Point.plusAssign(other: Point) {
    x += other.x
    y += other.y
}

// -=
operator fun Point.minusAssign(other: Point) {
    x -= other.x
    y -= other.y
}

// *=
operator fun Point.timesAssign(other: Point) {
    x *= other.x
    y *= other.y
}

// /=
operator fun Point.divAssign(other: Point) {
    x /= other.x
    y /= other.y
}

// %=
operator fun Point.remAssign(other: Point) {
    x %= other.x
    y %= other.y
}

```

需要注意的是两个情况：

- 如果没有定义 plusAssign 函数，只定义了 plus 函数。
  表达式 a += b ，就会变成 a = a + b ，会去找 plus函数来实现 += 操作
  此时 a 不能使用 val 来修饰，因为 val 不能重新赋值，只能使用 var 来修饰

- 如果同时定义了 plusAssign 、plus 函数。此时 a 只能通过 val 来修饰，这样 a += b 只会去找 plusAssign 函数

其他的复合操作符 `minusAssign、timesAssign、divAssign、remAssign` 也是同理

下面我们来测试下复合操作符：

```Kotlin

fun main(args: Array<String>) {
    val p1 = Point(10, 30)
    val p2 = Point(20, 30)

    p1 += p2
    println("plusAssign: $p1")

    p1 -= p2
    println("minusAssign: $p1")

    p1 *= p2
    println("timesAssign: $p1")

    p1 /= p2
    println("divAssign: $p1")

    p1 %= p2
    println("remAssign: $p1")
}

输出结果：

plusAssign: Point[30, 60]
minusAssign: Point[10, 30]
timesAssign: Point[200, 900]
divAssign: Point[10, 30]
remAssign: Point[10, 0]

```

### 一元操作符重载

介绍完复合操作符重载，我们再来看看算数操作符重载的最后一部分：一元操作符重载

`一元操作符` 顾名思义就是只有操作符一边有被操作的数据，例如：`+a、++a` 等

下面是一元操作符和对应的函数名称：

操作符示例 | 函数名
---   |   ---
+a  |  unaryPlu
-a  |  unaryMin
!a  |  not
\+\+a , a\+\+ |  inc
--a , a-- | dec

取反操作符（`!`），一般用于布尔，例如 `false.not()`

我们依然以 `Point` 作为例子：

```Kotlin
//-a
operator fun Point.unaryMinus(): Point {
    return Point(-x, -y)
}

//+a
operator fun Point.unaryPlus(): Point {
    return Point(+x, +y)
}

//++a
operator fun Point.inc(): Point {
    return Point(x + 1, y + 1)
}

//--a
operator fun Point.dec(): Point {
    return Point(x - 1, y - 1)
}

```

下面做一个简单的测试：

```
fun main(args: Array<String>) {
    var p1 = Point(10, 30)
    println(+p1)
    println(-p1)
    println(p1++)
    println(++p1)
}

输出结果：

Point[10, 30]
Point[-10, -30]
Point[10, 30]
Point[12, 32]

```

## 比较操作符重载

比较操作符 顾名思义就是用于比较大小的。比如是否相等、大于 等等

操作符 | 函数名
---|---
==     | equals()
!=     | !equals()
>、<、>=、<=  | compareTo()

Java 中 `==` 是比较地址是否相等。在 `Kotlin` 中 `==` 是 调用 `equals` 方法，如果要比较地址是否相等，使用 `===`

看似 Java 和 Kotlin 在比较相等的方式只是换了一个方式而已：Kotlin是使用 == 底层还是调用 equals方法，Java是使用 equals

其实不然，我们来看下 Kotlin是怎么来处理这个 == 的，经过反编译我们可以看出来是通过 Intrinsics.areEqual 方法来处理的：

```
public static boolean areEqual(Object first, Object second) {
    return first == null ? second == null : first.equals(second);
}
```
通过这个源代码得到2点收获：

- 相比 Java 的比较方式，在实际开发中，Kotlin的方式更加健壮，在 Java 中我们一般要判断对象是否为null，不为null才能调用它的equals()，否则可能会出现空指针异常。
- 代码非常优雅简洁，类似的判断对象是否相等的逻辑，很多人一般会嵌套了许多if语句，Kotlin是很好的学习素材。

说完 `equals` 方法，我们来看下用于重载大于等于操作符的 `compareTo` 函数。

为了更好的说明比较操作符，我们不在使用 `Potin` 类，我们新建一个 `Person` 类：

```
class Person(val name: String, val age: Int) : Comparable<Person> {
    override fun compareTo(other: Person): Int {
        //比较字段是可变参数，可以输入多个比较字段
        //先比较age，如果age相等，再比较name
        return compareValuesBy(this, other, Person::age, Person::name)
    }
    
    override fun equals(other: Any?): Boolean {
        val o = other as? Person
        return name == o?.name
    }
}

```

覆写 `compareTo` 函数的时候，可以使用 Kotlin 中 内置的 `compareValuesBy` 函数来帮我们实现，该函数可以比较多个字段，如上面注释所示。

下面我们测试下上面的例子：

```
fun main(args: Array<String>) {
    val p1 = Person("chiclaim", 20)
    val p2 = Person("pony", 20)
    
    println(p1 == p2)  //相当于：p1.equals(p2)
    println(p1 > p2)   //相当于：p1.compareTo(p2) > 0
    println(p1 >= p2)  //相当于：p1.compareTo(p2) >= 0
    println(p1 < p2)   //相当于：p1.compareTo(p2) < 0
    println(p1 <= p2)  //相当于：p1.compareTo(p2) <= 0
}
```


## 集合相关的操作符重载

### 索引操作符重载

这个的索引不单单指数组或集合的索引，这里的索引是泛指，能够映射到某个具体的值，都可以称之为索引。

所以这里索引称之为 key 更合适。其实数组或者集合都可以把索引理解成key，然后通过这个key映射到对应的值。

既然是操作符重载，就会有操作符对应特定的函数名称。索引操作符不仅可以获取值，还可以修改值，所以索引操作符对应两个函数名称，一个用来获取，一个用来修改：

操作符 | 函数名
---|---
[key]    | get、set

其中，`get` 函数用来获取值，`set` 用来修改值。

下面我以 Point 类来演示下索引操作符重载 :

```Kotlin

//通过属性的位置索引来获取对应的属性值
operator fun Point.get(index: Int): Int {
    return when (index) {
        0 -> x
        1 -> y
        else ->
            throw IndexOutOfBoundsException("Invalid coordinate $index")
    }
}

//通过属性的名字获取属性值
operator fun Point.get(propertyName: String): Int {
    return when (propertyName) {
        "x" -> x
        "y" -> y
        else ->
            throw IllegalArgumentException("Invalid propertyName $propertyName")
    }
}

//通过属性索引来修改对应的属性值
operator fun Point.set(index: Int, value: Int) {
    when (index) {
        0 -> x = value
        1 -> y = value
        else ->
            throw IllegalArgumentException("Invalid property index $index")
    }
}

//通过属性名称来修改对应的属性值
operator fun Point.set(propertyName: String, value: Int) {
    when (propertyName) {
        "x" -> x = value
        "y" -> y = value
        else ->
            throw IllegalArgumentException("Invalid propertyName $propertyName")
    }
}

```

下面来测试下上面的索引操作符：

```Kotlin
fun main(args: Array<String>) {
    val point = Point(100, 200)
    println("x=${point[0]}, y=${point[1]}") //相当于println("x=${point.get(0)}, y=${point.get(1)}")
    point["x"] = 500 //相当于 point.set("x", 500)
    point[1] = 500   //相当于 point.set(1, 500)
    println("x=${point["x"]}, y=${point["y"]}")
}

//输出结果
x=100, y=200
x=500, y=500

```

### 索引操作符重载Kotlin集合中的应用

上面介绍了索引操作符的重载，我们顺便来看下 `Kotlin集合` 中是怎么使用 `索引操作符` 的

比如 `MutableMap` 集合，它就重载了 `set` 和 `get` 函数：

```
@kotlin.internal.InlineOnly
public inline operator fun <@kotlin.internal.OnlyInputTypes K, V> Map<out K, V>.get(key: K): V? =
    @Suppress("UNCHECKED_CAST") (this as Map<K, V>).get(key)

@kotlin.internal.InlineOnly
public inline operator fun <K, V> MutableMap<K, V>.set(key: K, value: V): Unit {
    put(key, value)
}
```

需要注意的只有`MutableMap` 重载了 `set` 和 `get` 函数，`Collections.Map` 是一个不可变集合，不能修改，所以只重载了 `get` 函数。

关于集合的可变和不可变可以查看[从Java角度深入理解Kotlin](https://blog.csdn.net/johnny901114/article/details/85575213)的集合部分

这样一来我们就可以很方便的操作 `MutableMap` 集合的里的元素了：

```
val map = hashMapOf("chiclaim" to 28) //声明一个可变集合
map["chiclaim"] = 18 //相当于 map.put("chiclaim", 18)
println(map["chiclaim"])  //相当于 println(map.get("chiclaim"))
```

### in 操作符重载

in 操作符有两个作用：
- 用于判断某个值是否存在于某个范围内
- 用于遍历数组或者集合

所以 in 操作符对应了两个函数名称：

操作符 | 函数名
---|---
in    | contains、iterator

可能有些读者会问了，如果在某个类中，同时重载了 `contains` 和 `iterator` 两个函数怎么办？

Kotlin 会根据你使用 in 操作符的不同而去寻找不同的函数

例如 in 操作符用于 for 遍历中 : `for(value in "chiclaim")` ，Kotlin 则会去寻找 `iterator` 函数

如果你是用这样使用 in 操作符的 如：`val has = 'c' in "chiclaim"`，Kotlin 则会去寻找 `contains` 函数

简而言之，就是如果 in 操作符用于遍历的时候，就会对应 `iterator` 函数；除此之外会去寻找 `contains` 函数

下面使用一个简单的例子来说明 in 操作符的使用：

首先新建一个矩形类：

```
data class Rectangle(val upperLeft: Point, val lowerRight: Point)

//重载 in 操作符
//主要逻辑就是判断某个点，是否在矩形内
operator fun Rectangle.contains(p: Point): Boolean {
    return p.x in (upperLeft.x until lowerRight.x) &&
            (p.y in upperLeft.y until lowerRight.y)
}

```
上面的 `until` 是一个区间函数，关于区间操作符我们下面会介绍到，就把他当做一个区间类就好了。

也就是说

这个点的 `x` 坐标 是否在 矩形的 `x` 坐标区间内

这个点的 `y` 坐标 是否在 矩形的 `y` 坐标区间内

只有这两个条件同时成立，`contains` 函数才返回 true

需要注意是 `until` 函数返回的区间是一个开区间，不包括末尾的元素。比如：`0 until 10 = [0 ~ 9]`

下面测试下 in 操作符在集合中和区间中的使用：

```
val rect = Rectangle(Point(10, 10), Point(60, 60))

// in 操作符用于区间判断
println(Point(20, 20) in rect)  //true
println(Point(5, 20) in rect)   //false
val a = 'c' in "chiclaim"  //contains()

// in 遍历字符串
for(value in "chiclaim"){
    ...
}

// in 操作符用于集合遍历
val list = listOf(1,2,3)
for (i in list){
    ...
}

```

## 区间操作符重载

区间 顾名思义就是只一个范围(Range)的意思，这里的区间是指闭区间

区间操作符与其对应的函数名称如下所示：

操作符 | 函数名
---|---
..    | rangeTo

上面我们介绍的 `until` 函数底层也是使用了区间操作符 `..` 来实现的：

```
public infix fun Int.until(to: Int): IntRange {
    if (to <= Int.MIN_VALUE) return IntRange.EMPTY
    return this .. (to - 1).toInt()
}
```

`util` 函数 创建的区间是一个开区间，不包括最后一个元素，`..` 操作符创建的区间是包含头和尾的。


任何实现了 `Comparable` 接口的都可以使用区间操作符，因为 `Kotlin` 为 `Comparable` 提供了名为 `rangeTo` 的扩展函数：

```Kotlin
public operator fun <T : Comparable<T>> T.rangeTo(that: T): ClosedRange<T> = ComparableRange(this, that)
```

我们使用 `until` 或 `..` 创建的区间返回的是什么？返回的值实际上一个实现了 `ClosedRange` 接口的对象


下面我们来看下 `Kotlin in Action` 为我们提供的例子，很好的阐述了区间操作符的用法：

```
val nowDate = LocalDate.now()
//声明一个区间(假期)，假期从现在开始算起，为期10天
val vacation = nowDate..nowDate.plusDays(10)
//假期的第七天，是否属于这个假期内，答案肯定是true
println(nowDate.plusWeeks(1) in vacation)
```

我们还可以使用我们上面定义的 `Person` 类来使用下 `区间操作符`，因为它也实现了 `Comparable` 接口：

```
val personRange = Person("chiclaim",28)..Person("steve",56)

val zucker = Person("zuckerberg",34)

println(zucker in personRange)

```

我们首先声明了一个关于 人的区间 ，然后判断 `zucker` 是否在这个区间内。

我们通过 `in` 操作符来判断是否在区间内，所以它去找 `contains` 函数，这个 `contains` 函数是在 `ClosedRange` 中定义的：

```
public operator fun contains(value: T): Boolean = value >= start && value <= endInclusive

```
也就是判断 `zucker` 这个人是否大于等于区间的第一个人，小于等于区间的最后一个人。

判断大小是通过 `compareTo` 这个函数，所以又会去找 `Person` 类的 `compareTo` 函数，我们回顾下它的比较逻辑：

```
override fun compareTo(other: Person): Int {
    //先比较age，如果age相等，在比较name
    //比较字段是可变参数，可以输入多个
    return compareValuesBy(this, other, Person::age, Person::name)
}
```
也就是说先比较年龄，然后在比较名字，所以上面的 `zucker in personRange` 肯定返回 true，因为 34 处于 28 和 56 之间

通过这个例子呢，我们串联起来我们之前讲过的 `比较操作符重载`



## 解构操作符重载

解构操作符重载和其对应的函数名称表：

操作符示例 | 函数名
---|---
(variables)    | componentN

从上面的表格中，我们知道解构操作符是一对 `()`，解构出来的数据放在 `()` 中间，我们把这样的声明称之为解构声明 (`destructuring declaration`)

解构声明和普通的变量声明类似，但是解构声明是在一对括号中声明多个变量的，如：

```
val p = Point(10, 20)
//解构声明
val (x, y) = p
```
那么什么样的对象能够使用解构声明呢？

在类中重载了组件函数 `componentN` ，为什么叫 `componentN` 呢？因为一个类中可能会有多个成员变量，你可能在解构声明中声明多个变量

一个变量对应一个 `componentN` 函数，在解构声明中的第一个变量对应 `component1`，第二个对应 `component2`，后面以此类推

下面我们在 `Point` 类中来定义几个组件函数，这些函数也是使用 `operator` 关键来修饰：

```
operator fun Point.component1() = x

operator fun Point.component2() = y

```

如何使用解构声明：

```
fun main(args: Array<String>) {
    val p1 = Point(10, 19)
    
    //解构声明
    val (x, y) = p1
    //上面的解构声明相当于：
    //val x = p1.component1()
    //val y = p1.component2()
    
    println("x=$x , y=y$y")
}
```

在Kotlin中，如果你定义了一个 `data class` 并且这个 `data class` 有构造函数和参数，Kotlin 会根据构造函数的参数自动为我们生成 `component` 函数

比如我们这样来定义 `Point`

```
data class Point(var x: Int, var y: Int) {

}

```
反编译查看其对应的 Java 代码，会自动生成 `component` 函数：

```
public final class Point {
   private int x;
   private int y;

   //去掉其他关代码...

   public final int component1() {
      return this.x;
   }

   public final int component2() {
      return this.y;
   }
}
```

数组集合也可以直接使用解构声明如：

```
val (v1, v2, v3, v4, v5) = listOf(1, 2, 3, 4, 5)
```

但是数组和集合的解构声明只能声明5个变量，换句话说只能访问容器的  `前五个元素`

所以如果某个函数返回值是一个数组或者集合，你可以直接使用解构声明来接受你关心的前五个元素


解构声明 这个特性在函数返回值上非常有用，这样就可以间接的返回多个值了，例如：

```
fun getPoint(): Point {
    return Point(1, 2)
}

val (x1, y1) = getPoint()
```

在实际开发中，一个函数可能需要返回多个值。比如，某个函数我们需要返回一个无序数组中的最小值和最大值。

如果要在Java中实现，比较复杂。在 Kotlin 中就非常简单，这个时候我们可以只用 Kotlin 内置 `Pair` 类：

```
fun getMaxMin(): Pair<Int, Int> {
    //省略获取最大值最小值的逻辑...

    val min: Int = 5
    val max: Int = 10
    //直接将要返回的值直接扔给Pair对象
    return Pair(min, max)

}

//通过解构声明，直接接受最大值和最小值
val (min, max) = getMaxMin()

```

如果你要返回3个值，你可以使用 `Kotlin` 内置的 `Triple` 类


解构声明不仅可以使用在类似上面的顶级声明(top-level statemengs)中，还可以使用在遍历中：

```Kotlin
//解构操作符用于 集合遍历
val map = hashMapOf("name" to "chiclaim", "address" to "hangzhou")
for ((key, value) in map) {
    println("$key -> $value")
}
```

我们来分析下上面的遍历语句 ：`for ((key, value) in map)`

首先 `in` 操作符 对应的是 `iterator` 函数：

```Kotlin
public inline operator fun <K, V> MutableMap<K, V>.iterator(): MutableIterator<MutableMap.MutableEntry<K, V>> = entries.iterator()
```

所以遍历出来的是 `MutableEntry`，该接口继承了 `Map.Entry<K, V>`，Kotlin 为 `Map.Entry` 定义了 `component1` 和 `component2` 扩展函数:

```Kotlin
@kotlin.internal.InlineOnly
public inline operator fun <K, V> Map.Entry<K, V>.component1(): K = key

@kotlin.internal.InlineOnly
public inline operator fun <K, V> Map.Entry<K, V>.component2(): V = value

```

所以解构声明能直接用在遍历 `map` 的语句中。

简而言之就是重载了 `componentN` 函数的类，那就能使用解构声明。

## 属性委托(delegated properties)

虽然属性委托中用到的操作符重载不多，但是属性委托的概念在本文中是最复杂一块。

在介绍属性委托之前，我们先来看看什么是委托 (`delegation`)。

委托是一种设计模式，称之为委托模式 (`delegation pattern`)

委托模式是某个对象接受请求时，将这个请求委托给另一个对象处理。例如：

```
class Lawyer {
     void defend() { 
       System.out.print("defend..."); 
     }
 }
 
class People { 
     Lawyer lawyer = new Lawyer(); 
     void defend() {
       p.defend(); // delegation
     } 
 }
 
 public static void main(String[] args) {
     People p = new People();
     p.defend();
 }
 
```

上面代码的意思就是当人们需要辩护的时候一般是委托律师来替自己辩护。

我们再来看下 `Kotlin` 中的 `属性委托`，属性委托顾名思义就是在访问该属性的时候(一般通过getter、setter来访问)，委托另一个属性对象来处理

如下面的伪代码：

```
class Foo {
    private val delegate = Delegate()
    
    var p: Type
    set(value: Type) = delegate.setValue(..., value) 
    get() = delegate.getValue(...)
}

```

当访问 `p`  成员属性的时候，里面是通过 `delegate` 对象来处理的。

在 `p` 的 `setter` 和 `getter` 方法里都需要手动去调用 `delegate` 的 `setValue` 和 `getValue` 方法

为了简化代码量 `Kotlin` 使用 `by` 关键字 后面跟上代理的对象

上面的伪代码可以简化成下面的形式：

```
class Foo {
    var p: Type by Delegate() 
}
```

能简化成上面形式还有一个前提是 `Delegate` 类必须有 `setValue` 和 `getValue` 函数，函数使用 `operator` 关键字修饰：

```
class Delegate {
    operator fun getValue(...) { ... }
    operator fun setValue(..., value: Type) { ... }
}
```


## 总结

本文讲解了关于 Kotlin 操作符重载的方方面面，掌握这些操作符有助于我们阅读 Kotlin 源代码以及更好的使用 Kotlin API。

如果在实际的开发中给自己定义的类型进行操作符重载，需要注意代码是否更加可读了，例如 person + person，阅读代码的人不知道是 person.age 相加 还是 person.salary 相加

操作符重载其实很简单，就是一个操作符对应一个固定名字的函数而已。

最后的属性委托可能要复杂一点，虽然它也用到了操作符重载，但它侧重的一种的思想，而不是操作符重载。
