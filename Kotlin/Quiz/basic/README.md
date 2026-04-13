### 1. 변수교환
```kotlin
fun main() {
    print("첫 번째 값 입력: ")
    val aInput = readLine()!!.toInt()

    print("두 번째 값 입력: ")
    val bInput = readLine()!!.toInt()

    var a = aInput
    var b = bInput

    // 임시 변수 사용
    val temp = a
    a = b
    b = temp

    println("교환 후 결과:")
    println("a = $a, b = $b")
}
```
