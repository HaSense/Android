1. QuizClient는 Windows 폴더에 존재하는 Android App
2. FastAPIServer 폴더는 QuizClient와 연동되는 FastAPI 백엔드 서버를
   작성하라.
3. Server의 상태를 웹으로도 확인하고 싶기에 front를 HTML5 + CSS 형태로 작성하라.
4. 프로그램은 minoconda `fast`가상 환경에서 동작한다.
5. 서버 연속 동작을 위한 systemd service 파일을 작성하라.
   service 파일 이름은 quizAndroid1.service로 하라
6. 통신은 wsl -d Ubuntu에서 동작 예정이다.
7. QuizClient와 Server 부분을 작성하고 지금은 CPP 서버는 작성하지 말아라.
8. 중간에 특이 사항 및 필수 상황이 있다면 agents.md에 요약 기록하라.
9. 첫 sample이니 코드를 읽기 좋은 간단한 화면과 직곽적으로 읽히는 코드로 작성하라.
10. Android는 Kotlin언어가 아닌 java 언어로 작성하라.
11. gradle버전은 QuizClient에 있는 gradle버전과 맞추어라.
12. 2024-06-06 FastAPIServer 폴더에 FastAPI + SQLite 기반 서버, HTML5 대시보드, QuizClient 연동 및 quizAndroid1.service 예제 유닛 파일 작성 완료. 서비스 실행 경로/사용자는 환경에 맞게 조정할 것.
13. DB사용은 sqlite로 하라. 스키마는 직접 작성하라.
14. Quiz문제와 등록된 문제 Android client 모두 한글로 작업을 처리하라.

