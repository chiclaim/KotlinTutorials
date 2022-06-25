package annotation

import annotation.custom.Parameter
import annotation.custom.FiledOnly
import annotation.custom.Normal
import annotation.custom.PropertyGetterOnly

/**
 *
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */
class AnnotationUseSite(@field:FiledOnly val name:String,    // annotate Java field
                        @get:PropertyGetterOnly val age:Int,      // annotate Java getter
                        @param:Parameter val sex:Int) {   // annotate Java constructor parameter

    @property:Normal
    @setparam:Normal
    var address:String? = null

    @delegate:Normal
    val phone:String by lazy {
        "10086"
    }

    // 不指定 use-site
    @UnknownUseSite
    var city:String?=null
}

fun @receiver:Normal AnnotationUseSite.printInfo(){
    println("${this.name},${this.address}")
}

val @receiver:Normal AnnotationUseSite.email:String
    get() {
        return "chiclaim@gmail.com"
    }

// 如果你不指定 use-site，kotlin 会根据注解的 AnnotationTarget 来确定 use-site target
// 如果 AnnotationTarget 指定了多个值，那么 target 会按照 param/property/field 顺序来匹配。

@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
annotation class UnknownUseSite



/*
The full list of supported use-site targets is:

file

property (annotations with this target are not visible to Java)

field

get (property getter)

set (property setter)

receiver (receiver parameter of an extension function or property)

param (constructor parameter)

setparam (property setter parameter)

delegate (the field storing the delegate instance for a delegated property)


 */