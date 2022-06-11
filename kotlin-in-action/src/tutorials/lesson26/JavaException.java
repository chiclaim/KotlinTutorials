package tutorials.lesson26;

/**
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */
public class JavaException {

    //unchecked exception 包括 RuntimeException, Error, 和他们的子类

    // checked exception
    static void testCheckedException() throws Throwable{

    }

    // unchecked exception
    static void testUnCheckedException() throws NullPointerException{

    }

    // checked exception
    static void testError() throws StackOverflowError{
        testError();
        // do something ...
    }


    public static void main(String[] args) {
        try {
            testCheckedException();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        testUnCheckedException();

        // 不要对 error 进行 try catch
        try {
            testError();
        } catch (StackOverflowError e) {

        }
        System.out.println("after error");



    }
}