


# Kotlin 注解

- 注解的定义
- Kotlin 元注解
  - @Target
  - @Retention
  - @Repeatable
  - @MustBeDocumented
- use-site target
  - @file       the class containing top-level functions and properties declared in the file
  - @property:  annotations with this target are not visible to Java
  - @field:     the field generated for the property
  - @get:       property getter
  - @set:       property setter
  - @receiver:  receiver parameter of an extension function or property
  - @param:     constructor parameter
  - @setparam:  property setter parameter
  - @delegate:  the field storing the delegate instance for a delegated property