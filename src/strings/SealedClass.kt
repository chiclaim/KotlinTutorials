package strings

sealed class Expr {

    class Num(val value: Int) : Expr()
    class Sum(val left: Expr, val right: Expr) : Expr()

}

fun eval(e: Expr): Int =
        when (e) {
            is Expr.Num -> e.value
            is Expr.Sum -> eval(e.left) + eval(e.right)
        }


fun main(args: Array<String>) {
    val sum = eval(Expr.Sum(Expr.Num(1), Expr.Num(2)))
    println("sum = $sum")
}