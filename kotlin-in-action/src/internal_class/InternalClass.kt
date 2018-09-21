package internal_class

/**
 * desc: internal修饰的class只在当前module可见
 *
 * Created by Chiclaim on 2018/09/20
 */

//internal修饰的class只对本module可见是如何实现的？

internal open class InternalClass {
    fun getName(): String {
        return "chiclaim"
    }
}