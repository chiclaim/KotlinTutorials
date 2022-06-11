package tutorials.lesson26;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */
public class JavaException2 {


    void test1() {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


}