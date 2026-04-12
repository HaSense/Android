# 코틀린 프로그래밍 문법

| 컬렉션타입 | 설명 |
|---------|-----|
|List|연속된 상품|
|Set|중복되지 않은 순서없는 모음|
|Map|Key-Value맵핑 데이터|


### List

```kotlin
// 읽기전용
val readOnlyShapes = listOf("사과", "수박", "딸기")
println(readOnlyShapes)
// [사과, 수박, 딸기]

// 명시적 변경 타입 선언 목록
val shapes: MutableList<String> = mutableListOf("사과", "수박", "딸기")
println(shapes)
// [사과, 수박, 딸기]
```


java는 클래스에 public을 붙이지 않으면 다른 패키지 일때 접근불가. 하지만 코틀린은 기본족으로 class 앞에 public을 붙이지 않아도 코틀린에서는 기본이 public이 붙어 있는 형태임.

### 상속
```kotlin
abstract  class Shape {
    abstract fun draw()
}

class Triangle : Shape() {
    override fun draw() {
        println("삼각형을을 그리다")
    }

}
class Rectangle : Shape() {
    override fun draw() {
        println("사각형을 그리다")
    }

}
class Circle : Shape() {
    override fun draw() {
        println("원을 그리다")
    }

}
```

#### 구구단
```kotlin

fun main(){
    print("출력할 단을 입력하세요. (2~9)")

    val input = readLine()?.toIntOrNull()

    //입력값 검증
    if(input == null || input !in 2..9){
        println("잘못된 입력입니다. 2~9 사이의 숫자를 입력하세요.")
        return
    }

    for(i in 1..9){
        println("$input x $i = ${input * i}")
    }
}
```





