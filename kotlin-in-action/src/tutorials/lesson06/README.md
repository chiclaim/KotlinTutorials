
为什么要讲下 Kotlin 数据类型和访问修饰符修饰符呢？因为 Kotlin 的数据类型和访问修饰符和 Java 的还是有些区别的，所以单独拎出来说一下。

## Kotlin 数据类型

我们知道，在 Java 中的数据类型分基本数据类型和基本数据类型对应的包装类型。如 Java 中的整型 int 和它对应的 Integer包装类型。

在 Kotlin 中是没有这样的区分的，例如对于整型来说只有 Int 这一个类型，Int 是一个类（姑且把它当装包装类型），我们可以说在 Kotlin 中在编译前只有包装类型，为什么说是编译前呢？因为编译时会根据情况把这个整型( `Int` )是编译成 Java 中的 `int` 还是 `Integer`。 那么是根据哪些情况来编译成基本类型还是包装类型呢，后面会讲到。我们先来看下 Kotlin和 Java 数据类型对比：

|Java基本类型  | Java包装类型 |	Kotlin对应 |
|------    |   ------    |   ------    |
|char	   |java.lang.Character	 | kotlin.Char |
|byte	   |java.lang.Byte	     | kotlin.Byte |
|short	   |java.lang.Short	     |kotlin.Short |
|int	   |java.lang.Integer	 |kotlin.Int   |
|float	   |java.lang.Float	     |kotlin.Float |
|double	   |java.lang.Double	 |Kotlin.Double|
|long	   |java.lang.Long	     |kotlin.Long  |
|boolean   |java.lang.Boolean	 |kotlin.Boolean|



乍一看，只是换了一个名字而已，其实内部细节远不止这些。

举个例子，`var num:Int?` 编译后 num 是 Integer 类型，但是 `var num: Int = 0` 编译后 num 是 int 基本类型。

从中可以看出代码的细微差别，会导致编译后的代码有比较大的差别。

下面来分析下哪些情况编译是成 Java 中的基本类型还是包装类型。下面以整型为例，其他的数据类型同理。

### 1. 如果变量可以为null

如果变量可以为null(使用操作符`?`)，则编译后是包装类型。如：

```kotlin
//因为可以为 null，所以编译后为 Integer
var width: Int? = 10
var width2: Int? = null
```

编译后的代码：

```java
public final class MainKt {
    @Nullable
    private static Integer width = 10;
    @Nullable
    private static Integer width2;

    @Nullable
    public static final Integer getWidth() {
        return width;
    }

    public static final void setWidth(@Nullable Integer var0) {
        width = var0;
    }

    @Nullable
    public static final Integer getWidth2() {
        return width2;
    }

    public static final void setWidth2(@Nullable Integer var0) {
        width2 = var0;
    }
}
```

再来看看方法返回值为整型：
```kotlin
//返回值 Int 编译后变成基本类型 int
fun getAge(): Int {
    return 0
}

//返回值 Int 编译后变成 Integer
fun getAge2(): Int? {
    return 0
}
```
反编译后：

```java
public final class MainKt {
   public static int getAge() {
      return 0;
   }

   @Nullable
   public static Integer getAge2() {
      return 0;
   }
}
```

所以声明变量后者方法返回值的时候，如果声明可以为 null，那么编译后时是包装类型，反之就是基本类型。

### 2. 如果作为泛型参数

如果作为泛型则参数编译后是包装类型，如集合泛型、数组泛型等。

```kotlin
//集合泛型
//集合里的元素都是 Integer 类型
fun getAge3(): List<Int> {
    return listOf(22, 90, 50)
}

//数组泛型
//会编译成一个 Integer[]
fun getAge4(): Array<Int> {
    return arrayOf(170, 180, 190)
}
```


看下编译后的代码：

```java
class Test { // must be in a Class
    @NotNull
    public static final List getAge3() {
        return CollectionsKt.listOf(new Integer[]{22, 90, 50});
    }

    @NotNull
    public static final Integer[] getAge4() {
        return new Integer[]{170, 180, 190};
    }
}
```


### 3.  如何声明基本类型数组
上面的列子中，声明的数组的元素是包装类型，如果如果想要声明的数组编译后是基本类型的数组，需要使用 xxxArrayOf(...)，如 intArrayOf：

```kotlin
//会编译成一个int[]
fun getAge5(): IntArray {
    return intArrayOf(170, 180, 190)
}
```

当然，除了 intArrayOf，还有 charArrayOf、floatArrayOf 等等，就不一一列举了。

### 4. Kotlin 数据类型为什么不共用 Java 那一套

我们都知道，Kotlin 是基于 JVM 的一款语言，编译后还是和 Java 一样。那么为什么不像集合那样直接使用 Java 那一套，要单独设计一套这样的数据类型呢？

Kotlin 中没有基本数据类型，都是用它自己的包装类型，包装类型是一个类，那么我们就可以使用这个类里面很多有用的方法。下面看下 Kotlin in Action 的一段代码：

```kotlin
fun showProgress(progress: Int) {
    val percent = progress.coerceIn(0, 100)
    println("We're $percent% done!")
}
```


编译后的代码为：
```java
class Test{ // must be in a Class
    public static final void showProgress(int progress) {
        int percent = RangesKt.coerceIn(progress, 0, 100);
        String var2 = "We're " + percent + "% done!";
        System.out.println(var2);
    }
}
```

从中可以看出，在开发阶段我们可很方便地使用 Int 类扩展函数。编译后，依然编译成基本类型 int，使用到的扩展函数的逻辑也会包含在内。

关于 Kotlin 中的数据类型就讲到这里，下面来看下访问修饰符。


## Kotlin 访问修饰符

我们知道访问修饰符可以修饰类，也可以修饰类的成员。下面通过两个表格来对比下 Kotlin 和 Java 在修饰类和修饰类成员的异同点：

表格一：类访问修饰符：

|类访问修饰符 |	Java可访问级别 | Kotlin可访问级别 |
|---          |---              |-------------|
| public	     |均可访问	       | 均可访问        |
| protected	 |同包名	       | 同包名也不可直接访问  |
| internal	 |不支持该修饰符   | 同模块内可见      |
| default	     |同包名下可访问   | 相当于public   |
| private      |当前文件可访问   | 当前文件可访问     |

表格二：类成员访问修饰符：

| 成员修饰符	 |Java可访问级别   | Kotlin可访问级别 |
|---|---              |---|
| public	 |均可访问	       | 均可访问      |
| protected	 |同包名或子类可访问  | 	只有子类可访问  |
| internal	 |不支持该修饰符	  | 同模块内可见    |
| default	 |同包名下可访问 	  | 相当于public |
|  private |当前文件可访问	  | 当前文件可访问   |

通过以上两个表格，有几点需要讲一下。

### 1. internal 修饰符

internal 修饰符是 Kotlin 独有而 Java 中没有的。internal 修饰符意思是只能在当前模块访问，出了当前模块则不能被访问。

**需要注意的是，如果 A 类是 internal 修饰，B 类继承 A 类，那么 B 类也必须是 internal 的，因为如果 kotlin 允许 B 类声明成public 的，那么 A 就间接的可以被其他模块的类访问。**

也就是说在 Kotlin 中，子类不能放大父类的访问权限。类似的思想在 protected 修饰符中也有体现，下面会讲到。

### 2. protected 修饰符在 Kotlin 和 Java 中的异同点

#### 1) protected 修饰类

我们知道，如果 protected 修饰类，在 Java 中该类只能被同包名下的类访问。

这样也可能产生一些问题，比如某个库中的类 A 是 protected 的，开发者想访问它，只需要声明一个类和类A相同包名即可。

而在 Kotlin 中就算是同包名的类也不能访问 protected 修饰的类。

为了测试 protected 修饰符修饰类，我在写 demo 的时候，发现 protected 修饰符不能修饰 Java 的顶级类，只能放在内部类上。

为什么不能修饰顶级类？

一方面，在 Java 中 protected 修饰的类，同包名可以访问，default 修饰符已经有这个意思了，把顶级类再声明成 protected 没有什么意义。

另一方面，在 Java 中 protected 如果修饰类成员，除了同包名可以访问，不同包名的子类也可以访问，如果把顶级类声明成protected，也不会存在不同包名的子类了，因为不同包名无法继承 protected 类

在 Kotlin 中也是一样的，protected 修饰符也不能修饰顶级类，只能修饰内部类。


在 Kotlin 中，同包名不能访问 protected 类，如果想要继承 protected 类，需要他们在同一个内部类下，如下所示：

```kotlin
open class ProtectedClassTest {
    protected open class ProtectedClass {
        open fun getName(): String {
            return "chiclaim"
        }
    }

    protected class ProtectedClassExtend : ProtectedClass() {
        override fun getName(): String {
            return "yuzhiqiang"
        }
    }
}

```


除了在同一内部类下，可以继承 protected 类外，如果某个类的外部类和 protected 类的外部类有继承关系，这样也可以继承protected 类

```kotlin
class ExtendKotlinProtectedClass2 : ProtectedClassTest() {
    private var protectedClass: ProtectedClass? = null
    //继承protected class
    protected class A : ProtectedClass() {
    }
}

```

需要注意的是，继承 protected 类，那么子类也必须是 protected，这一点和 internal 是类似的。Kotlin 中不能放大访问权限，能缩小访问权限吗？答案是可以的。

可能有人会问，既然同包名都不能访问 protected 类，那么这个类跟私有的有什么区别？确实，如果外部类没有声明成 open，编译器也会提醒我们此时的 protected 就是 private

所以在 Kotlin 中，如果要使用 protected 类，需要把外部声明成可继承的 (open)，如：

```kotlin
//继承 ProtectedClassTest
class ExtendKotlinProtectedClass2 : ProtectedClassTest() {
    //可以使用 ProtectedClassTest 中的 protected 类了
    private var protectedClass: ProtectedClass? = null
}

```

#### 2) protected修饰类成员

如果 protected 修饰类成员，在 Java 中可以被同包名或子类可访问；在 Kotlin 中只能被子类访问。

这个比较简单就不赘述了


#### 3) 访问修饰符小结

1. 如果不写访问修饰符，在 Java 中是 default 修饰符 (package-private)；在 Kotlin 中是 public 的
2. internal 访问修饰符是 Kotlin 独有，只能在模块内能访问的到
3. protected 修饰类的时候，不管是 Java 和 Kotlin 都只能放到内部类上
4. 在 Kotlin 中，要继承 protected 类，要么子类在同一内部类名下；要么该类的的外部类和 protected 类的外部类有继承关系
5. 在 Kotlin 中，继承 protected 类，子类也必须是 protected 的
6. 在 Kotlin 中，对于 protected 修饰符，去掉了同包名能访问的特性
7. 如果某个 Kotlin 类能够被继承，需要 open 关键字，默认是 final 的

