package reflection.framework

import higher_order_function.joinToString
import reflection.framework.annotations.Field
import reflection.framework.annotations.ID
import reflection.framework.annotations.Table
import tutorials.lesson10.println
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.jvm.jvmName

/**
 *
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */
abstract class Dao<T> : IDao<T> {
    override fun add(): Boolean {
        // insert into (id,name,author) values(?,?,?)
        val klass = javaClass.kotlin
        val tableName = klass.findAnnotation<Table>()?.name ?: javaClass.simpleName
        val map = linkedMapOf<String, Any?>().apply {
            klass.declaredMemberProperties.filter {
                it.annotations.isNotEmpty()
            }.forEach {
                it.findAnnotation<ID>()?.let { propertyAnnotation ->
                    put(propertyAnnotation.name, it.get(this@Dao))
                    return@forEach
                }
                it.findAnnotation<Field>()?.let { propertyAnnotation ->
                    put(propertyAnnotation.name, it.get(this@Dao))
                    return@forEach
                }
            }
        }
        val fields = map.keys.joinToString(",")
        val values = map.values.joinToString(",")
        val sql = "insert into $tableName ($fields) values ($values)"
        println(sql)
        return true
    }

    override fun update(): Boolean {
        return false
    }

    override fun delete(): Boolean {
        return false
    }

    override fun select(): List<T> {
        return emptyList()
    }
}