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
### 4. 홀/짝수 판별
- 
```kotlin
fun main() {
    val num = readLine()!!.toInt()

    if (num % 2 == 0) {
        println("짝수")
    } else {
        println("홀수")
    }
}
```

### 5. 구구단
- while
```kotlin
fun main() {
    print("단 입력: ")
    val n = readLine()!!.toInt()

    var i = 1
    while (i <= 9) {
        println("$n x $i = ${n * i}")
        i++
    }
}
```

- for
```kotlin
fun main() {
    print("단 입력: ")
    val n = readLine()!!.toInt()

    for (i in 1..9) {
        println("$n x $i = ${n * i}")
    }
}
```

### 6. Call by Reference 형태의 Swap(a,b) 함수 구현
- java에서도 c/c++ 같은 swap함수가 구현이 되지 않는다.
- Kotlin에서는 생성자 앞에 var 또는 val을 붙이면 자동으로 멤버로 승격됩니다.
```kotlin
class IntRef(var value: Int) //value는 멤버가 됨

fun swap(a: IntRef, b: IntRef) {
    val temp = a.value
    a.value = b.value
    b.value = temp
}

fun main() {
    val a = IntRef(readLine()!!.toInt())
    val b = IntRef(readLine()!!.toInt())

    swap(a, b)

    println("a = ${a.value}, b = ${b.value}")
}
```

### 7. 사칙연산 프로그램 
- 단  Calculator 클래스를 만드세요.
```Kotlin
class Calculator {

    fun add(a: Int, b: Int): Int = a + b

    fun sub(a: Int, b: Int): Int = a - b

    fun mul(a: Int, b: Int): Int = a * b

    fun div(a: Int, b: Int): Double = a.toDouble() / b
}

fun main() {
    print("첫 번째 정수 입력: ")
    val a = readLine()!!.toInt()

    print("두 번째 정수 입력: ")
    val b = readLine()!!.toInt()

    val calc = Calculator()

    println("덧셈: ${calc.add(a, b)}")
    println("뺄셈: ${calc.sub(a, b)}")
    println("곱셈: ${calc.mul(a, b)}")

    if (b != 0) {
        println("나눗셈: %.2f".format(calc.div(a, b)))
    } else {
        println("나눗셈: 0으로 나눌 수 없습니다.")
    }
}
```

### 상속

```kotlin
open class Hero {
    open fun attack() {
        println("공격!")
    }
}

class Knight : Hero() {
    override fun attack() {
        println("Knight: 검으로 공격!")
    }
}

class Archer : Hero() {
    override fun attack() {
        println("Archer: 활로 공격!")
    }
}

class Wizard : Hero() {
    override fun attack() {
        println("Wizard: 마법으로 공격!")
    }
}

fun main() {
    // 부모 타입으로 자식 객체 참조 (다형성)
    val heroes: Array<Hero> = arrayOf(
        Knight(),
        Archer(),
        Wizard()
    )

    // 동일한 호출 → 서로 다른 결과
    for (hero in heroes) {
        hero.attack()
    }
}
```






