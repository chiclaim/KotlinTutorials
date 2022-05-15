package tutorials.lesson04;

import java.util.Random;

/**
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */
public class JavaSwitchTest {

    public static void main(String[] args) {
        String value = findValue();
        test(value);
    }

    static String findValue() {
        if (new Random().nextInt() == 1) {
            return "";
        }
        return null;
    }

    static void test(String value) {
        switch (value) {
            case "one":
                System.out.println("1");
            case "two":
                System.out.println("2");
                break;
            case "three":
                System.out.println(3);
                break;
        }
    }
}
