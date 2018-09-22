package lambda;

import java.util.Arrays;
import java.util.List;

/**
 * desc: 在 lambda中不能修改方法参数值，也不能修改lambda外面的变量值
 * <p>
 * Created by Chiclaim on 2018/09/22
 */

public class JavaLambdaModifyVariable {
    private static void printGoods(String prefix, List<String> goods) {
        int count = 0;
        goods.forEach(value -> {
                    //不可修改, Kotlin的lambda可以
                    //count++;
                    System.out.println(prefix + " " + value);
                }
        );
    }

    public static void main(String[] args) {
        printGoods("china", Arrays.asList("telephone", "tv"));
    }
}
