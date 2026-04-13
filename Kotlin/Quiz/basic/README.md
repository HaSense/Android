### 1. 변수교환
- 두 정수값을 입력받아 출력하되 a, b 변수값이 변경되게 작성해 보세요.
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

### 2. 섭씨를 화씨로 바꾸는 프로그램
- 소수점 둘째자리까지 구하세요.

```kotlin
fun main() {
    val celsius = readLine()!!.toInt()

    val fahrenheit = 9.0 / 5 * celsius + 32

    // 소수점 둘째 자리까지 출력
    println(String.format("%.2f", fahrenheit))
}
```
