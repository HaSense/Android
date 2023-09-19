Android 10 (API29) 이후로 또 한번 SD카드 정책이 변경되었습니다.

따라서 AndroidManifest.xml의 퍼미션에 3개를 추개해야 합니다.

<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<!-- Android 10 (API level 29) 이상에서는 다음 퍼미션도 추가해야 합니다. -->
<uses-permission android:name="android.permission.MANAGE_EXTERNAL_STORAGE" />


그리고 정책상 SD카드 파일저장은 이제 공용 폴더는 안되고 해당 프로그램의 하위 경로에 저장됩니다.
프로그램 삭제 시 파일도 모두 삭제됩니다.
