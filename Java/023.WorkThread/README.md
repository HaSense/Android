## 루퍼 (Looper)

**메시지큐 (Message Queue)**: 메시지가 저장되는 장소. 

**메시지나 Runnable 객체** : 일단 큐에 저장되고 들어온 순서대로 순차적으로 처리됨

**루퍼 (Looper)**: 메시지 큐에서 메시지를 꺼내어 핸들러로 전달. 

**루퍼**는 무한히 실행되는 메시지 루프를 통해 큐에 메시지가 들어오는지 감시하며, 들어온 메시지를 처리할 핸들러를 찾아 `handleMessage` 메서드를 호출합니다.

![루퍼 이미지](img/Looper1.png)

![image](https://github.com/HaSense/Android/assets/23206779/6f7feaff-70f7-4c44-8d32-e4782f311e9c)

