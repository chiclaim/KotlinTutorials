package new_class

/**
 * desc: internal修饰的class只在当前module可见
 *
 * Created by Chiclaim on 2018/09/20
 */

//internal修饰的class只对本module可见是如何实现的？

internal class Person7 {
    fun getName(): String {
        return "chiclaim"
    }
}