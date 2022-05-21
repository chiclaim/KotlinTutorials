package base

// String 表示 receiver type
// this 表示 receiver object
fun String.lastChar(): Char = this.get(this.length - 1)

