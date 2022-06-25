package annotation

import kotlin.concurrent.thread

annotation class AnnotationLambda


fun main() {
    // 在 lambda 上使用注解
    thread @AnnotationLambda{

    }
}