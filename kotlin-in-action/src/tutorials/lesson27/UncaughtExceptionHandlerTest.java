package tutorials.lesson27;

/**
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */
public class UncaughtExceptionHandlerTest {


    public static void main(String[] args) throws InterruptedException {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            // 全局处理异常
            System.out.println("哦吼，发生异常了："+e.getMessage());
        });

        while (true){
            Thread.sleep(1000);
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    throw new NullPointerException("NPE");
                }
            }.start();
        }
    }
}
