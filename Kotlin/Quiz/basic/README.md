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

### 3. 국어, 영어, 수학 성적의 총점과 평균 구하기
- 일반형
```kotlin
fun main() {
    print("국어 점수 입력: ")
    val kor = readLine()!!.toInt()

    print("영어 점수 입력: ")
    val eng = readLine()!!.toInt()

    print("수학 점수 입력: ")
    val math = readLine()!!.toInt()

    val totalScore = kor + eng + math
    val avg = totalScore / 3.0

    println("총점: $totalScore")
    println("평균: %.2f".format(avg))
}
```
- 배열형 (국어,영어,수학 순서로 들어온다고 가정)
```kotlin
fun main() {
    // 입력: 공백으로 구분된 3개의 점수 (국어 영어 수학)
    val scores = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    val totalScore = scores.sum()
    val avg = totalScore / scores.size.toDouble()

    println("총점: $totalScore")
    println("평균: %.2f".format(avg))
}
```
- 안전입력형
```kotlin
fun main() {
    val scores = IntArray(3)

    val subjects = arrayOf("국어", "영어", "수학")

    for (i in scores.indices) {
        print("${subjects[i]} 점수 입력: ")
        scores[i] = readLine()!!.toInt()
    }

    val total = scores.sum()
    val avg = total / scores.size.toDouble()

    println("총점: $total")
    println("평균: %.2f".format(avg))
}
```












