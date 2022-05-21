
主要内容：
- 函数的定义
- 顶级函数
- 定义函数参数默认值
- 调用函数时给定参数名
- 单表达式函数
- 中缀调用
- 扩展函数
- 局部函数
- 匿名函数
- 函数可变参数及原理分析
- Java Arrays.asList 可变参数的坑和原理分析
- 展开操作



前面我们已经介绍了函数的定义和组成，下面在继续分析函数的其他方面

## 更方便的函数调用

### 调用函数时指定参数的名字

假设我们有如下的函数：

```kotlin
fun <T> joinToString(collection: Collection<T>,
    separator: String, 
    prefix: String, 
    postfix: String): String
```

然后调用该函数(为参数值指定参数名称)：

```kotlin
joinToString(collection, separator = " ", prefix = " ", postfix = ".")
```


### 为函数参数指定默认值

我们可以把 **joinToString** 定义改成如下形式：

```kotlin
fun <T> joinToString(collection: Collection<T>, 
    separator: String = ", ", 
    prefix: String = "", 
    postfix: String = "")
```

我们分别为函数的最后三个参数都设置了默认值，我们可以这样调用该函数：

```kotlin
joinToString(list)
joinToString(list, prefix = "# ")
```

这样也就间接的实现了Java中所谓的**重载(overload)**，代码也更简洁，不用定义多个方法了

### Parameter和Argument的区别

看过 **《Kotlin In Action》** 的英文原版细心的同学可能会发现：书中的 **3.2.1** 章节是 **Named Arguments**

直译过来是：为参数命名。作者为什么没有写成 **Named Parameters** 呢？

下面我们就来看下 **Parameter 和 Argument 的区别**

简而言之，就是在定义函数时候的参数称之为 **Parameter**；调用函数传入的参数称之为 **Argument**

如下图所示：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190507105733521.png)
因为 **《Kotlin In Action》** 的 **3.2.1** 章节是讲调用函数的时候为参数命名，所以使用了 **Arguments**

此外，除了 Parameter 和 Argument ，还有 **Type Parameter 和 Type Argument**

因为下面还要用到这两个的概念，所以这里我们介绍下 **Type Parameter 和 Type Argument**

Type Parameter 和 Type Argument 的概念是在泛型类或者泛型函数的时候出现：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190507105751856.png)

## 顶级函数和属性

在 Java 中我们需要把函数和属性放在一个类中

在 Kotlin 中我们可以把某个函数或属性直接放到某个 Kotlin 文件中

把这样的函数或属性称之为 顶级(top level)函数或属性

例如在 **join.kt** 文件中：
```
package strings

fun joinToString(...): String { 
    ... 
}
```

在 Java 代码中如何调用该方法呢？因为 JVM 虚拟机只能执行类中的代码

所以 Kotlin 会生成一个名叫 **JoinKt** 的类，并且顶级函数是静态的

所以可以在 Java 中这样调用顶级函数：

```
JoinKt.joinToString(...)
```

在Kotlin中如何调用，如果在不同的包，需要把这个顶级函数导入才能调用

```
//相当于 import strings.JoinKt.joinToString
import strings.joinToString 

//相当于 import strings.JoinKt.*
import strings.* 
```

所有的工具类都可以使用这样的方式来定义

**顶级属性** 同样也是 static 静态的

如果使用 **var** 来定义会生成对应的**静态setter、getter**函数

如果使用 **val** 来定义只会生成对应的**静态getter**函数

我们知道顶级函数和属性，最终还是会编译放在一个类里面，这个类名就是顶级函数或属性的 **Kotlin文件名称+Kt**

如果所在的Kotlin文件名被修改，编译生成的类名也会被修改，可以通过注解的方式来固定编译生成的类名：

```
@file:JvmName("StringFunctions")

package strings
fun joinToString(...): String { 
    ... 
}
```

调用的时候就可以这样来调用：

```
import strings.StringFunctions; 

StringFunctions.joinToString(list, ", ", "", "");
```

## 扩展函数

何谓 **扩展函数** ？ 扩展函数是在类的外部定义，但是可以像类成员一样调用该函数

扩展函数的定义格式如下图所示：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190507105825891.png)


其中 **receiver type** 就是我们扩展的目标类，**receiver object** 就是目标类的对象(哪个对象调用该扩展函数，这个this就是哪个对象)

**lastChar** 就是我们为 **String** 类扩展的函数

```
package strings

fun String.lastChar(): Char = this.get(this.length - 1)
```

然后我们这样来调用该扩展函数：

```
println("Kotlin".lastChar())
```

如果扩展函数所在的包名和使用地方的包名不一样的话，需要导入扩展函数

```
import strings.*
//或者
import strings.lastChar

val c = "Kotlin".lastChar()
```


## 扩展函数原理分析

扩展函数本质上是静态函数，如上面的扩展函数 lastChar 反编译后对应的 Java 代码：

```
public static final char lastChar(@NotNull String $receiver) {
  Intrinsics.checkParameterIsNotNull($receiver, "receiver$0");
  return $receiver.charAt($receiver.length() - 1);
}
```

编译的时候，会在调用的该扩展函数的地方使用 **StringUtilsKt.lastChar("")** 代替

所以，如果要在 Java 中使用 Kotlin 定义的扩展函数，也是直接调用该静态方法即可

并且扩展函数是不能被覆写(override) 的，因为它本质上是一个静态函数

## 扩展属性

扩展属性和扩展函数的定义非常相似：

```
val String.lastChar: Char 
    get() = this.get(length - 1)
```

我们必须为这个扩展属性定义 **getter** 函数，因为扩展属性没有 **backing field**

扩展属性在定义的时候，也会生成静态方法：

```
public static final char getLastChar(@NotNull String $receiver) {
  Intrinsics.checkParameterIsNotNull($receiver, "receiver$0");
  return $receiver.charAt($receiver.length() - 1);
}
```

如果扩展属性的 **receiver object** 可以被修改，可以把扩展属性定义成 **var**

```
var StringBuilder.lastChar: Char
    get() = get(length - 1) 
    set(value: Char) {
        this.setCharAt(length - 1, value) 
    }
```

## 函数的可变参数和展开操作符

### 可变参数

在 Java 中通过三个点(**...**)来声明可变参数，如：

```
public static <T> List<T> listOf(T... items) {
	System.out.println(items.getClass()); //数组类型
	return Arrays.asList(items);
}
```

Kotlin 和 Java 不一样，Kotlin 使用 **vararg** 关键来定义可变参数：

```
fun <T> listOf(vararg items: T): List<T> {
    println(items.javaClass)     //数组类型
    return Arrays.asList(*items) // * spread operator
}
```
对于可变参数的函数，调用它的时候可以传递任意个参数

### 展开操作符

通过上面的两段代码比较我们发现：Kotlin 需要显示的将可变参数通过 **\*** 展开，然后传递给 **asList** 函数

这里的 **\*** 就是 **展开操作符(spread operator)**，在 Java 中是没有 **展开操作符** 的


下面我们再来看下，展开操作符的方便之处：

```
val intArr: Array<Int> = arrayOf(1, 2, 3, 4)
Arrays.asList(0, intArr).run {
	println("size = $size")
}

//输出结果：
size = 2
```

可以发现，不用展示操作符的话，集合里面只有两个元素

那我们把它改成使用 **展开操作符** 的情况：

```
val intArr: Array<Int> = arrayOf(1, 2, 3, 4)
Arrays.asList(0, *intArr).run {
	println("size = $size")
}

//输出结果：
size = 5
```

### Java中的Arrays.asList()的坑和原理分析

既然上面用到了 Java 中的 **Arrays.asList()** 函数，下面来讲下该函数的容易遇到的坑及原理分析：

```
public static void testArrays() {
	int[] intArr = {1, 2, 3};
	List list = Arrays.asList(intArr);
	println(list.size());   //size = 1
}

public static void testArrays2() {
	Integer[] intArr ={1, 2, 3};
	List list = Arrays.asList(intArr);
	println(list.size());  //size = 3
}
```

上面的 **testArrays** 和 **testArrays2** 函数非常相似，只不过是数组的类型不同，导致 **Arrays.asList(arr)** 返回的集合大小不一样

只要是 **原始类型数组** Arrays.asList 返回的集合大小为 1，如果是 **复杂类型的数组**，Arrays.asList 返回的集合大小为数组的大小

为什么会产生这种情况呢？下面来分析下：

首先看下 **Arrays.asList** 是怎么定义的：

```
public static <T> List<T> asList(T... a)
```

Java 中的可变参数相当于数组：

```
public static <T> List<T> asList(T[] a)
```

我们知道 Java 中的泛型必须是复杂类型，所以这里的泛型 **T** 也必须是 **复杂类型**

当我们传递 **int[]** 数组的时候，就会出现问题，因为 **int 是原始类型，T 是复杂类型**

所以 **int[]** 赋值给 **T[]** 是非法的，当 **一维原始类型的数组** 当做给可变参数的时候，编译器会把这个可变参数编译成一个 **二维数组**

**这*就是为什么会出现上面情况的原因***

我们再来看下 **Arrays.asList** 完整源码：

```

public static <T> List<T> asList(T... a) {
	return new ArrayList<>(a);
}

private static class ArrayList<E> extends AbstractList<E>
	implements RandomAccess, java.io.Serializable
{
	private static final long serialVersionUID = -2764017481108945198L;
	private final E[] a;

	ArrayList(E[] array) {
		a = Objects.requireNonNull(array);
	}
	//省略其他...
}
```

经过上面的分析我们知道，如果是一维原始类型的数组传递给可变参数，这个可变参数就是 **二维数组**

然后把二维数组传递给内部ArrayList的构造方法，通过 **E[]** 保存下来。这里的泛型 **E** 就相当于 **int[]**，**E[]** 相当于 **int[][]**


需要注意是 Java 不允许 将个二维数组 **直接赋值** 给一维的泛型数组：

```
int[][] intArray = {{1},{2}};
T[] t = intArray;  //非法
```

但是 Java 允许 **把二维数组传递给参数是一维的泛型数组的函数**，如：

```
public static <T> void testGeneric(T[] data){
}
int[][] intArray = {{1},{2}};
testGeneric(intArray);
```

### Kotlin 展开操作符的原理分析

讲到这里你可能迫不及待的想知道，为什么我们上面的代码使用了展开操作符 **Arrays.asList(\*intArr)** 返回的集合大小就是 5 呢？

```kotlin
val intArr: Array<Int> = arrayOf(1, 2, 3, 4)
Arrays.asList(0, *intArr).run {
	println("size = $size")
}

//输出结果：
size = 5

```

反编译后对应的 Java 代码如下：

```
Integer[] intArr2 = new Integer[]{1, 2, 3, 4};
SpreadBuilder var10000 = new SpreadBuilder(2);
var10000.add(0);             //第1个元素
var10000.addSpread(intArr2); //数组里的4个元素
List var2 = Arrays.asList((Integer[])var10000.toArray(new Integer[var10000.size()]));
int var7 = false;
String var5 = "size = " + var2.size();
System.out.println(var5);
```
原来会通过 **SpreadBuilder** 来处理展开操作符，**SpreadBuilder** 里面维护了一个**ArrayList**

所有的元素都会保存到这个 **ArrayList** 中，然后把这个集合转成 **元素为复杂类型数组**，再传给 **Arrays.asList(arr)** 函数

根据上面我们对 **Arrays.asList(arr)** 的分析，我们就知道返回的集合大小是 **5** 了

## 中缀调用

我们都知道什么是前缀(prefix)，后缀(suffix)。那什么是函数的中缀(infix)调用呢？

使用关键字 **infix** 修饰的函数都能够 **中缀调用**

被关键字 **infix** 修饰的函数只能有一个参数

Kotlin 中的 **to** 就是一个中缀函数：

```
public infix fun <A, B> A.to(that: B): Pair<A, B> = Pair(this, that)
```

下面我们来对比下 **to** 函数的常规调用和中缀调用：

```
1.to("one")  //普通的函数调用
1 to "one"   //函数的中缀调用
```

除了 **to** 函数，还有我们介绍 **循环** 的时候讲到的 **until、downTo、step** 也是中缀函数：

```kotlin
public infix fun Int.until(to: Int): IntRange {
    if (to <= Int.MIN_VALUE) return IntRange.EMPTY
    return this .. (to - 1).toInt()
}

public infix fun Int.downTo(to: Int): IntProgression {
    return IntProgression.fromClosedRange(this, to, -1)
}

public infix fun IntProgression.step(step: Int): IntProgression {
    checkStepIsPositive(step > 0, step)
    return IntProgression.fromClosedRange(first, last, if (this.step > 0) step else -step)
}


//使用示例：
for(i in 0 until 100){
}

for (i in 100 downTo 0 step 2) {
}
```


## 局部函数

**局部函数(local function)** 是在函数里面定义函数，局部函数只能在函数内部使用j局部函数说白了就是函数嵌套，那什么时候使用局部函数呢？当一个函数里的逻辑很多重复的逻辑，可以把这些逻辑抽取到一个局部函数

以《Kotlin In Action》的代码为例：

```kotlin
fun saveUser(user: User) {
    if (user.name.isEmpty()) {
        throw IllegalArgumentException("Cannot save user ${user.id}: Name is empty")
    }
    if (user.address.isEmpty()) { 
        throw IllegalArgumentException("Cannot save user ${user.id}: Address is empty")
    }
    // Save user to the database 
}
```
这个 **saveUser** 函数里面有些重复逻辑，如果 name 或 address 为空都会抛出异常

可以使用局部函数优化下：

```kotlin
fun saveUser(user: User) {
    fun validate(value: String, fieldName: String) { 
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user ${user.id}: " + "$fieldName is empty")
        }
    }
    validate(user.name, "Name") 
    validate(user.address, "Address")
    // Save user to the database   
}
```

局部函数避免了模板代码的出现。如果不使用局部函数，我们需要把 **validate函数** 定义到外面去，但是这个函数只会被 **saveUser函数** 使用到，从而污染了外面的全局作用域。通过局部函数使得代码更加清晰，可读性更高。

需要注意的是，虽然 Kotlin 允许在函数内部定义函数，但是不要嵌套太深，否则会导致可读性太差

## 匿名函数

匿名函数顾名思义就是没有名字的函数：如：

```kotlin
fun(x: Int, y: Int): Int {
    return x + y
}
```
匿名函数的返回类型的推导机制和普通函数一样：

```kotlin
fun(x: Int, y: Int) = x + y
```

如果声明了一个匿名函数 ，如何调用呢？

```kotlin
(fun(x: Int, y: Int): Int {
    val result = x + y
    println("sum:$result")
    return result
})(1, 9)

//输出结果：
//sum:10
```