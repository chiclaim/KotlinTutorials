package tutorials.lesson27

/**
 *
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */
fun watchTry(type: String) {
    var orderType: Int? = -1
    try {
        orderType = type.toInt()
    } catch (e: NumberFormatException) {
        e.printStackTrace()
        orderType = null
    }
    if (orderType != null) {
        // todo something
    }
}

fun watchTry2(type: String) {
    // 将低层次异常进行封装，避免模板代码
    val orderType = type.toIntOrNull()
    if (orderType != null) {
        // todo something
    }
}