package tutorials.lesson13;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */
public class Introspection {
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(PF.class);

        PropertyDescriptor[] proDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor prop : proDescriptors) {
            System.out.println("pro name : "+prop.getName());
        }
    }

}
