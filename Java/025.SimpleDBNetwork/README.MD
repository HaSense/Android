# Android - Backend - DB 아키텍처 정리

## 기본 구조


Android 앱 ↔ HTTP/HTTPS ↔ REST API 백엔드 ↔ DB


- 데이터 교환: **JSON**
- Android 아키텍처: **data layer / repository 패턴 권장**

---

## 정확한 구조 이해

- ❌ Android ↔ DB 직접 연결 (권장하지 않음)
- ✅ Android ↔ 백엔드 서버 ↔ DB

즉,

- Android는 **WSL에서 구동 중인 백엔드(FastAPI, Spring, ASP.NET Core 등)**에 REST 호출
- 백엔드가 DB와 통신

---

## 왜 이 구조가 좋은가

### 1. 낮은 결합도 (Low Coupling)
- DB 변경 (SQLite → MySQL/PostgreSQL)
- 스키마 변경

→ **API 계약만 유지하면 앱 수정 최소화**

---

### 2. 보안성
- DB 계정/포트 노출 방지
- 인증, 권한, 입력 검증, 로깅 → 서버에서 통제

---

### 3. 모바일 환경 적합성
- IP 변경, 네트워크 끊김 대응
- 상태 유지(DB 세션)보다  
  → **Stateless HTTP 요청이 안정적**

---

### 4. 확장성
- Android / Web / iOS / Admin 페이지 → API 재사용 가능

---

## REST + JSON이 적합한 경우

- 게시판
- 주소록
- 재고 조회
- 주문 조회
- 일정 관리
- MES 현황 조회
- 설비 상태 조회 (비실시간)
- 회원가입 / 로그인 / CRUD

→ **전형적인 Request-Response 구조**

---

## REST만으로 부족한 경우

- 실시간 채팅
- 주식/센서 실시간 데이터
- 설비 상태 스트리밍
- 양방향 이벤트
- 지연 민감 제어

---

## 권장 혼합 구조

| 영역 | 기술 |
|------|------|
| 일반 데이터 | REST + JSON |
| 실시간 이벤트 | WebSocket / SSE / MQTT |

---

## 스마트팩토리 기준 정리

### REST 적합
- 설비 명령 이력 조회
- 생산 실적 조회
- 알람 목록 조회

### 실시간 통신 필요
- 설비 상태 스트리밍
- 알람 푸시
- 라이브 모니터링

---

## 권장 기술 스택

### Android
- Kotlin
- Retrofit / OkHttp

### Backend
- FastAPI 또는 ASP.NET Core

### 데이터
- JSON

### 인증
- JWT 또는 세션 기반

### DB
- PostgreSQL / MySQL / SQLite(개발용)

---

## 확장 요소

### 실시간 필요 시
- WebSocket 추가

### 오프라인 대응
- Android Room DB (로컬 캐시)

---

## 핵심 결론

- REST + JSON 구조는 **가장 안정적인 기본 아키텍처**
- 특히:
  - Android 앱
  - WSL 기반 개발 서버
  - DB 연동

→ 초기 설계로 **최적**

---

## 최종 정리

### 올바른 구조


Android ↔ Backend(API) ↔ DB


### 잘못된 구조


Android ↔ DB (직접 연결 ❌)


---

## 한 줄 결론

> RESTful API + JSON은 기본 전략으로 매우 적절하지만,  
> 실시간 요구가 생기면 WebSocket/MQTT를 반드시 병행해야 한다.