package visibility_modifier.modifier_class

/**
 * desc: internal修饰的class只在当前module可见
 *
 * Created by Chiclaim on 2018/09/20
 */

internal open class InternalClass {
    fun getName(): String {
        return "chiclaim"
    }
}