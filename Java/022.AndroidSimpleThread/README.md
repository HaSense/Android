# 핸들러 (Handler) 🚀

**핸들러**는 스레드 간의 통신을 가능하게 하는 중요한 장치입니다.

## 🌟 주요 특징

1. **통신 장치**: 스레드 간의 메시지나 `Runnable` 객체를 통해 메시지를 주고 받습니다.
2. **스레드 연관**: 핸들러는 항상 하나의 스레드와 관련이 있습니다. 이는 핸들러가 자신을 생성하는 스레드에 부착되며, 그 스레드의 메시지 큐를 통해 다른 스레드와 통신하기 때문입니다.
3. **메서드 호출**: 메시지가 도착하면 `handlerMessage(Message msg)` 메서드가 호출됩니다.

## 📌 메시지 (Message)

`Message` 객체는 스레드 간의 통신 내용을 저장하는 객체입니다. 추가 정보를 받기 위해 여러 필드를 가지며, `arg1`, `arg2` 등의 필드를 포함합니다.


| 필드 | 설명 |
|------|------|
| int what | 메시지 ID |
| int arg1 | 추가정보 1 |
| int arg2 | 추가정보 2 |
| Object obj | 임의의 객체로 추가정보 제공 |
| Messenger replyTo | 메시지에 대한 응답을 받은 객체를 지정|

- `boolean Handler.sendEmptyMessage(int what)`
- `boolean Handler.sendMessage(Message msg)`
- `boolean sendMessageAtFrontOfQueue(Message msg)`

