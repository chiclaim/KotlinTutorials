# 数组类型和它的扩展函数

## 数组的创建方式


### 方式1

通过 Array 类来创建数组。Array 类的声明如下：

```
public class Array<T>
```

其中泛型参数 `T` 就是数组的元素类型。既然是使用到了泛型，那么里面的元素都是复杂类型。

我们可以通过 Array 的构造方法来创建一个数组，构造方法的第一个参数是数组的大小，第二个参数是 lambda 表达式用来初始化数组的元素：

```

// public inline constructor(size: Int, init: (Int) -> T)
val a  = Array(3){
    9
}

```

上面的代码反编译后相当于：

```
Integer[] a = new Integer[3];
for(int i = 0; i < a.length; i++) {
   Integer v = 9;
   a[i] = v;
}
```
也就是创建了大小为 3 的 Integer 数组，每个元素的值都是 9


### 方式2

还可以通过 arrayOf 方法来创建数组，该方法的声明如下所示：

```
public inline fun <reified @PureReifiable T> arrayOf(vararg elements: T): Array<T>
```

该方法接收一个可变参数，用于设置数组的值。

```
val a = arrayOf(9,9,9)
```

反编译后的代码相当于：

```
Integer[] a = new Integer[]{9, 9, 9};
```

### 方式3

还可以使用 `XXXArray` 系列类来创建数组，如 ShortArray、IntArray 等等。

可以通过它的构造方法创建数组，它有两个构造方法：

```
//IntArray(size: Int)
//public inline constructor(size: Int, init: (Int) -> Int)

val a = IntArray(3)

val a1 = IntArray(3){
    9
}
```
上面的代码相当于：

```
  int[] a = new int[3];
  int[] a1 = new int[3];

  for(int i = 0; i < 3; ++i) {
     byte v = 9;
     a1[i] = v;
  }
```

### 方式4

还可以通过 XXXArrayOf 方法来创建数组：shortArrayOf、intArrayOf 等等

```
val a = intArrayOf(9,9,9)
```
反编译后代码相当于：

```
int[] a = new int[]{9,9,9};
```



### 创建多维数组

创建多维数组我们可以通过 `Array` 类或者 `arrayOf` 方法来创建。

```
Array(3){
    arrayOf(0,0,0)
}
```

上面代码反编译后相当于：

```
Integer[][] a = new Integer[3][];

for(int i = 0; i < a.length; ++i) {
  Integer[] item = new Integer[]{0, 0, 0};
  var1[i] = item;
}
```

在来看下 arrayOf 方法创建多维数组:

```
val a = arrayOf(arrayOf(1,2,3), arrayOf(1,2,3))

```

上面代码反编译后相当于：

```
Integer[][] a = (Integer[][])(new Integer[][]{{1, 2, 3}, {1, 2, 3}});
```

我们不难发现上面的数组类型是 `Integer[][]`，如果想让其类型是 `int[][]` 该怎么做呢？

```
// int[][] a2 = (new int[][]{{1, 2, 3}, {1, 2, 3}});
val a2 = arrayOf(
        intArrayOf(1,2,3),
        intArrayOf(1,2,3)
    )
```

只要让最里面一维数组使用的 `XXXArrayOf` 即可，例如下面的 3 维数组：

```
// int[][][] a3 = new int[][][]{new int[][]{{1, 2, 3}}, new int[][]{{1, 2, 3}}};
val a3 = arrayOf(
    arrayOf(
        intArrayOf(1,2,3)
    ),
    arrayOf(
        intArrayOf(1,2,3)
    )
)
```

## 数组的操作

对于一些简单的操作函数就不一一用代码举例了，直接通过方法名就能知道功能，例如下面的一些函数

- max:返回数组最大值
- min:返回数组最小值
- binarySearch 二分查找
- component1 返回数组的第一个元素
- ...

我们来看下其他的一些操作符。

### map 操作

对数组中的每个元素执行 transform 函数，然后将该函数的返回值放入 List 中，最后返回整个 List

```
fun map(){
    val array = intArrayOf(2,4,6)
    array.map {
        it*2 // 返回单个元素
    }.also {
        println(it)
    }
}

// 输出 [4, 8, 12]
```

### flatMap 操作

对数组中的每个元素执行 transform 函数，该函数返回一个迭代器，然后将迭代器中的元素全部放入 List 中，最后返回整个 List

```
fun flatMap(){
    val array = intArrayOf(2,4,6)
    array.flatMap {
        listOf(it*2) // 返回迭代器
    }.also {
        println(it)
    }
}
// [4, 8, 12]
```

### fold 操作

fold有两个参数 initial（初始值），operation（函数，参数是initial和数组元素），对数组每个元素执行 operation 操作，然后将返回值重新赋值给 initial，然后返回累加器

```
fun fold(){
    val array = intArrayOf(2,4,6)
    array.fold(10){acc, i ->
        acc*i
    }.also {
        println(it)
    }
    
}

//计算过程:
// 10*2=20
// 20*4=80
// 80*6=480
// 最后输出：480
```


### associate 操作

对数组中的每个元素执行 transform 函数，transform 函数返回值 Pair 作为 Map 中的 entry，然后返回 Map

```
fun associate(){
    val array = intArrayOf(2,2,6)
    array.associate {
        Pair(it, it.toString()) // entry
    }.also {
        println(it)
    }
}

// map 会自动去重
// {2=2, 6=6} 
```

### associateBy 操作

对数组中的每个元素执行 keySelector 函数，将 keySelector 函数返回的值作为 key，数组的元素作为 value，然后返回 Map

```
fun associateBy(){
    val array = intArrayOf(2,2,6)
    array.associateBy {
        it.toString() // key
    }.also {
        println(it)
    }
    
}
// {2=2, 6=6}
```

### distinct 操作

对数组进行去重

```
fun distinct(){
    intArrayOf(2,4,6,6).distinct().also {
        print(it)
    }
}

//[2,4,6]
```

### distinctBy 操作

对数组中的每个元素执行 selector 函数，数组元素作为 selector 函数参数，函数返回值作为 key，然后对 key 进行去重，非重复的就会放进 List，然后返回 List

```kotlin
fun distinctBy(){
    intArrayOf(1,2,4,6).distinctBy {
        it % 2 // key,如果 key 重复，则数组当前元素不放入 List 中
    }.also {
        println(it)
    }
}
// 2,4,6都是偶数，it % 2 = 0，所以重复了，这三个数只有 2 会放入 List 中
//[1, 2]
```










