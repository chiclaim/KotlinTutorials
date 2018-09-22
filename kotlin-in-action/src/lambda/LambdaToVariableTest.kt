package lambda

/**
 * desc:
 *
 * Created by Chiclaim on 2018/09/22
 */

//最多支持22参数 对应的处理类是 Function22

// N  -> FunctionN

val sum = { y1: Int, y2: Int, y3: Int, y4: Int, y5: Int, y6: Int, y7: Int, y8: Int, y9: Int, y10: Int, y11: Int,
            y12: Int, y13: Int, y14: Int, y15: Int, y16: Int, y17: Int, y18: Int, y19: Int, y20: Int, y21: Int, y22: Int ->
    y1 + y2
}

fun main(args: Array<String>) {
    println(sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22))


    run {
        println("run a lambda")
    }

}

/*
public final class LambdaToVariableTestKt {
   @NotNull
   private static final Function22 sum;

   @NotNull
   public static final Function22 getSum() {
      return sum;
   }

   public static final void main(@NotNull String[] args) {
      Intrinsics.checkParameterIsNotNull(args, "args");
      int var1 = ((Number)sum.invoke(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22)).intValue();
      System.out.println(var1);
   }

   static {
      sum = (Function22)null.INSTANCE;
   }
}


 */