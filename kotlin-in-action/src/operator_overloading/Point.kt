package operator_overloading

data class Point(var x: Int, var y: Int) {


    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }

    operator fun times(other: Point): Point {
        return Point(x * other.x, y * other.y)
    }

    operator fun div(other: Point): Point {
        return Point(x / other.x, y / other.y)
    }

    operator fun rem(other: Point): Point {
        return Point(x % other.x, y % other.y)
    }


}