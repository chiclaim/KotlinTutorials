package annotation

import annotation.custom.AnnotationInstance
import kotlin.concurrent.thread

/**
 *
 * @author kumushuoshuo
 * @github https://github.com/chiclaim/
 */


fun main(){
    fun test(annotationInstance: AnnotationInstance){

    }
    // 可以直接构造注解的实例
    test(AnnotationInstance())

    thread {

    }
}