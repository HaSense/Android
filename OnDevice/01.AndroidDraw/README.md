github의 그림판 모듈을 프로젝트에 삽입하는 방법을 서술해 놓았습니다.

jetpack과 완성된 코드를 활용해 프로젝트를 구현하는 방법을 살펴보는 예제입니다.

실습하는 교재를 보니 2021년8월에 나온 교재입니다. 

올해가 23년이니 2년 정도도 채 되지 않았지만 책을 작성한 시간도 있으니 도구의 변화

때문에 연동이 안되는 문제가 발생하고 있습니다.

[연동문제]
1. 그래들(gradle) 버전 문제
   - 교재에서 사용하고 있는 gradle 버전은 4.0으로 보입니다.
   - 23년 9월 현재 안드로이드는 gradle 버전은 8.0을 사용하고 있습니다.
   - 그래들이 업그레이드 되면 문법이 바뀔 수 있습니다. 확인합니다.

2. 호환성을 위해 해야할 행동
   - 안드로이드 스튜디오 IDE의 File메뉴 Settings 아래에 보면
     Progject Structure 메뉴가 있습니다. 여기서 그래들 버전을 맞춥니다.
     저는 8.0 기준으로 설명 드리겠습니다.

   - jetpack 저장소를 추가하는 부분이 settings.gradle로 변경되었습니다.
   ```
   dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
    }
    ```
    - build.gradle(모듈)에서 의존성을 추가하세요.
    ```
    dependencies {

    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.9.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    /******        추가된 모듈         *******/
    implementation 'com.github.divyanshub024:AndroidDraw:v0.1'
    /**********************************************************/

    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
    }
    ```

    - 그래들이 업그레이드 되면서 하위 호환성을 가진 jetpack 라이브러리들은 문제가 발생합니다.
    - 그래서 현재 모듈은 사용을 안하면 좋겠지만 연동하고 싶다면 하위호환성을 추가해 줘야 합니다.
    - gradle.properties에 android.enableJetifier = true 옵션을 추가합니다.
    ```
    org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8
    android.useAndroidX=true

    #Jetpack 하위호환성을 위해 다음 옵션을 추가해 주세요. update.2023.09.21
    android.enableJetifier=true
    ```
