package new_class;

/**
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */
public class Java8InterfaceMethod {

    public static void main(String[] args) {
        Male male = new YellowMale();
        male.print();

        Male.staticMethod();
    }
}


interface Male {
    default void print(){
        System.out.println("male...");
    }
    default void print2(){
        System.out.println("male2...");
    }
     static void staticMethod(){
        System.out.println("static...");
    }
}

class YellowMale implements Male{

    // 覆写 default 方法
    public void print(){
        System.out.println("yellow male");
    }

    /* default 只能在接口中使用
    default void dd(){
        System.out.println("kumushuoshuo");
    }*/
}