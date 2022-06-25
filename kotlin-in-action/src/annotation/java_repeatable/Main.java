package annotation.java_repeatable;

import annotation.custom.Tag;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */
public class Main {
    @Schedule(dayOfMonth = "last")
    @Schedule(dayOfWeek = "Fri", hour = 23)
    public void doPeriodicCleanup() {
    }

    // use kotlin repeatable annotation
    @Tag
    @Tag
    public void doJavaRepeatable() {
    }

    // 反射获取 repeatable 注解
    public static void main(String[] args) throws NoSuchMethodException {
        Method method = Main.class.getDeclaredMethod("doPeriodicCleanup");
        Annotation[] annotations = method.getAnnotationsByType(Schedule.class);

        System.out.println("注解："+annotations.length);
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

        method = Main.class.getDeclaredMethod("doJavaRepeatable");
        annotations = method.getAnnotationsByType(Tag.class);
        System.out.println("注解："+annotations.length);
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }
}
