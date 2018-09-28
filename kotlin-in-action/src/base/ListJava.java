package base;

import java.util.Arrays;
import java.util.List;

/**
 * Desc:
 * Created by Chiclaim on 2018/9/18.
 */
public class ListJava {
    public static List<Integer> getList() {
        return Arrays.asList(null, 1, 2, 3);
    }


    public static void updateList(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) + 100);
        }
    }
}
