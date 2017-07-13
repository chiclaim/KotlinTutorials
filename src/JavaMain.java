import strings.StringUtilsKt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dh on 2017/6/16.
 */
public class JavaMain {

    public static void main(String[] args) {

        //System.out.println(StringUtilsKt.lastChar("Java"));

//        List list = new ArrayList();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//
//        List list2 = Arrays.asList("prefix", list);
//
//        for (Object o : list2) {
//            System.out.println(o);
//        }
        String[] s = "12.345-6.A".split("\\.|-");
        for (String str : s) {
            System.out.println(str);
//        }
        }
    }
}
