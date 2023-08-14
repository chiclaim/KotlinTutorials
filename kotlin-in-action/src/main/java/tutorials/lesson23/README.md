

1. kotlin 1.6 when 对于 sealed class、enum、bool 的穷举
2. sealed class 可见性只有两个 private 和 protected，默认为 protected，如果为 private 只能通过内部类的方式声明子类，如果为 protected 可以在同包名下声明子类
3. 如果需要限定 sealed class 的子类只能在父类中定义，可以将构造方法声明为私有，private constructor，否则sealed class 的子类可以在当前包名下任意位置新建子类
4. sealed interface 无法设置私有构造方法，因为接口没有构造方法
5. sealed class 和普通类的区别在于
   1. sealed class 在结合 when 使用时具有'穷尽'特性，普通类则没有
   2. sealed class 在编译期就能确定所有的直接子类
6. 枚举是一组单例对象，而 sealed class 是一组 class，可以创建多个实例，如果对象不需要持有数据或者数据是不变的，可以使用枚举；如果需要创建对象，且对象的数据是变化的，可以使用 sealed class


