package annotation;

import annotation.custom.FieldOnly;
import annotation.custom.PropertyGetterOnly;

/**
 * Desc:
 * Created by Chiclaim on 2018/10/15.
 */
public class JavaBean {
    // @PropertyOnly Kotlin 定义的 Property 注解不能使用到 Java 字段上，可以使用AnnotationTarget.FIELD
    // 关于 Java 的 属性和字段的区别可以查看第 lesson13
    private String id;

    @FieldOnly
    private String name;

    // @PropertyOnly 编译器报错 error
    @PropertyGetterOnly
    public String getName() {
        return name;
    }

}
