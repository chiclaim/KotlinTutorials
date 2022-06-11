
Kotlin 异常

- Kotlin 没有 checked exception, 所有的异常都是 unchecked exception
- Kotlin exception 是一个表达式



探究 Kotlin 异常的设计理念
@Throws

- Java 异常分类
- 两个阵营一个是支持 checked exception，一个是反对 checked exception
- 支持方的观点
    - 官方1：根据其他语言的设计经验，以及多层多模块的大型稳定的系统都使用了受检查异常的事实
    - 检查异常对于函数式编程来说是非常糟糕的
- 不支持方的观点
    - 没有安全感
    -
- Kotlin 的观点


----

没有所谓的安全感，本来就是伪命题，如果是抛出 unchecked exception，Java 编译器也不会报错的，那是不是也不安全。


对代码中的 try-catch，要保持警惕：

```kotlin
  var orderType = -1
  try {
      orderType = Integer.parseInt("")
  } catch (e: NumberFormatException) {
      e.printStackTrace()
  }
```


有没有最佳实践呢？

导致滥用
实际上开发者是不会处理异常的
















