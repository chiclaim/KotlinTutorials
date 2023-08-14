package new_class

/**
 *
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */


open class Person7 {
    var name: String? = null
    var id: Int = 0


    constructor(name: String) {
        this.name = name
    }

    constructor(id: Int){
        this.id = id
    }

    //构造对象的时候，init代码块只会被执行一次
    init {
        System.out.println("init----------")
        System.out.println("init----------")
        System.out.println("init----------")
        System.out.println("init----------")
        System.out.println("init----------")
        System.out.println("init----------")
        System.out.println("init----------")
        System.out.println("init----------")
        System.out.println("init----------")
    }

}

// 反编译 init block 竟然是代码拷贝

/*

// android 自定义 View 示例
public class TestView extends View {

    public TestView(Context context) {
        super(context);
        init();
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
      // ....
    }
}

 */

