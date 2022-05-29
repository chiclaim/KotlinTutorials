

# 属性委托

- 主要内容：
    - by
    - by lazy
    - by Delegates.observable
    - by map
    - Kotlin 如何简化委托模式

- by 使用、原理
    - 属性字节码生成机制
- by 使用、原理
    - Lazy 机制
    - 实现类似的机制，需要注意的事项
    - Lazy 分析

- by Delegates.observable
    - 手动实现
    - 原理分析

1. 再次深入理解Kotlin属性 字节码生成机制：
    1. 不要机械的认为 val 修饰的属性，就会生一个 field 和 getter 方法
    2. 不要机械的认为 var 修饰的属性，就会生成一个 field 和对应的 getter 和 setter 方法
    3. 是否生成 field 取决于该属性是否存在 backing field


2. 扩展函数的坑
    1. 使用方便
    2. 有时候会增加代码理解困难 调用 getXXX(), Kotlin 直接使用 xxx 的方法

