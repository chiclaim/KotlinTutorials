package reflection.framework

/**
 *
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */
interface IDao<T> {
    fun add(): Boolean
    fun update(): Boolean
    fun delete(): Boolean
    fun select(): List<T>
}