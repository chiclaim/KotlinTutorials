package lambda.collection

import lambda.base.Person
import lambda.base.list

/**
 * desc: lazy collection和非 lazy collection 对比演示
 *
 * Created by Chiclaim on 2018/09/23
 */


fun main(args: Array<String>) {
    lazyCollectionTest()
}

fun collectionTest() {
    list.map(Person::age).filter { age ->
        age > 18
    }

    /*

    通过编译后对应的Java代码可以发现，经过map和filter函数，创建了两个临时集合：

    Iterable $receiver$iv = (Iterable)PersonKt.getList();
      //创建集合
      Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($receiver$iv, 10)));
      Iterator var4 = $receiver$iv.iterator();
      Object element$iv$iv;
      while(var4.hasNext()) {
         element$iv$iv = var4.next();
         Integer var11 = ((Person)element$iv$iv).getAge();
         destination$iv$iv.add(var11);
      }
      $receiver$iv = (Iterable)((List)destination$iv$iv);

      //创建集合
      destination$iv$iv = (Collection)(new ArrayList());
      var4 = $receiver$iv.iterator();

      while(var4.hasNext()) {
         element$iv$iv = var4.next();
         int age = ((Number)element$iv$iv).intValue();
         if (age > 18) {
            destination$iv$iv.add(element$iv$iv);
         }
      }
     */
}

fun lazyCollectionTest() {
    list.asSequence().map(Person::age).filter { age ->
        age > 18
    }.toList()
    //或者下面的遍历
    /*.forEach {
        println(it)
    }*/
}

/*
经过分析class字节码、对应的Java代码以及debug跟踪调试，lazy collection 有如下优点：

1，不会创建临时集合
2，用到集合元素的时候，如遍历或转化成新集合(forEach,toList)，才会触发集合的过滤、转化等操作。（某种意义上讲和RxJava有点类似）


 */



 
 