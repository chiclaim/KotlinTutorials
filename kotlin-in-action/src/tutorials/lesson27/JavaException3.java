package tutorials.lesson27;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */
public class JavaException3 {


    void test1() {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            // ignore
        }
    }

    void test2() throws Throwable{
        // 可能抛出多个异常，开发者为了方便，直接 throws Throwable
        JavaException4.testOther();
    }

    void test3(){
        try {
            JavaException4.testOther();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }





}