package tutorials.lesson27;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 滥用 Checked Exception
 *
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */
public class JavaAbusedCheckedException {

    //  抛出长长的异常列表
    void case1() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            FileNotFoundException, ClassNotFoundException {
        ThrowsTest.test1();
        ThrowsTest.test2();
        ThrowsTest.test3();
        ThrowsTest.test4();
    }

    // 为了避免长长的异常列表，可能会这样做：
    void case2() throws Throwable {
        ThrowsTest.test1();
        ThrowsTest.test2();
        ThrowsTest.test3();
        ThrowsTest.test4();
    }

    // 如果不对外抛出异常，可能会直接 try...catch 异常，避免
    void case3() {
        try {
            ThrowsTest.test1();
            ThrowsTest.test2();
            ThrowsTest.test3();
            ThrowsTest.test4();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    // 我们知道不会抛出异常，但是也不得不 try...catch
    // ByteArrayInputStream
    void test4() {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


}