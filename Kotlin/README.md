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

