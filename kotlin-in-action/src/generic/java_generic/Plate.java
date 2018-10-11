package generic.java_generic;

/**
 * Desc:
 *
 * <p>
 * <a href='https://www.zhihu.com/question/20400700'>Java Generic Wildcards</a>
 *
 * <p>
 * Created by Chiclaim on 2018/10/11.
 */

//定义一个`盘子`类
public class Plate<T> {

    private T item;

    public Plate(T t) {
        item = t;
    }

    public void set(T t) {
        item = t;
    }

    public T get() {
        return item;
    }

}

//水果类
class Fruit {
}

//苹果
class Apple extends Fruit {
}
//梨子
class Pear extends Fruit {
}





