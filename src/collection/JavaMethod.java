package collection;

/**
 * Created by dh on 2017/7/13.
 */
public class JavaMethod {

    public JavaMethod() {
    }

    public void postponeComputation(int delay, Runnable computation) {
        computation.run();
    }
}
