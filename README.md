# :purple_heart: Share U :purple_heart:
## 1. 작품 설명

 - 인천대학교 학생들끼리 과제 및 공부자료를 공유할 수 있는 모바일 앱 입니다.
 ![shareUgraphicImage](https://user-images.githubusercontent.com/40001772/80809597-f6fcbf00-8bfc-11ea-8fe0-49d9130bf1da.jpg)
 ![KakaoTalk_20200604_150151043](https://user-images.githubusercontent.com/40001772/83720666-7a825380-a674-11ea-8bec-66b7a0eff3ca.jpg)


## 2. 전체 기능
[shareU_홍보ppt.pdf](https://github.com/inu-appcenter/Share_U_Android/files/4303171/shareU_.ppt.pdf)

## 3. 역할
 - Android 전체

## 4. 개발 기간
 - 2020/01 ~ 2020/03

## 5. 개발 환경
 - Android
 - MySQL
 - Node.js

## 6. 개발 언어
 - Java
 - JavaScript

## 7. 주요 사용 라이브러리
 - Retrofit (통신)
 - PickIT (파일 업로드)
 - PRDownloader (파일 다운로드)
 
 ## 8. 주요 이슈 및 해결 방안
 프론트엔드가 백엔드보다 먼저 만들어진다.
 
 (...)
 ~~~
 시간 절약을 위해 postman의 mockserver를 이용해 가상 서버를 만들었고 미리 json 객체를 만들어 서버와 테스트 되는지 체크해보았다. 
 프론트엔드쪽의 무결성을 증명한다.
 ~~~
 
 뷰의 터치가 인식되지 않는다.
 ~~~
 Drawer와 Linear 순서로 View가 나열되어있으면 Drawer의 터치 이벤트가 작동하지 않는다.
 Android Touch Flow를 공부한 결과 최하위 자식 View 부터 터치를 할 건지 의사를 물어본다.
 계속 상위 자식들로 의사를 물어보다가 있으면 그 자식에게 터치 권한을 넘겨준다.
 ~~~
 
 파일 업로드 방식의 고민
 ~~~
 안드로이드 시스템은 파일을 선택하면 파일의 절대경로를 알려주는 것이 아니라 URI라는 것을 알려준다.
 URI는 파일의 식별자.
 파일 자체를 다루는 것은 어렵지 않지만 파일을 업로드 하기 위해서는 절대경로가 필요한데,
 안드로이드 8 이상부터는 일부 파일의 URI를 추출하는 것이 불가능하다.
 그래서 URI가 가리키는 파일의 복사본을 캐시 디렉토리에 새로 만들어서 캐시 디렉토리의 파일의 경로를 사용하는 방법을 채택했다.
 하지만 이 또한 단점이 존재한다.
 1. 파일의 복사본을 생성하는데 오버헤드가 발생한다. 2. 복사된 파일을 지워야한다.
 PickIT 라이브러리를 통해 1번 문제는 비동기 방식을 이용해서 오버헤드를 줄였고, 2번 문제는 액티비티 종료시 파일을 지워주는 메소드를 이용하였다.
 ~~~
 
 BottomSheetDialog 내부의 RecyclerView 안에 있는 데이터 전송
 ~~~
 자료를 업로드하는 Activity에서 과목 또는 교수명을 선택하는 경우가 있다.
 BottomSheetDialog 내부에 RecyclerView의 아이템을 클릭하면 그 정보를 Activity에 넘겨줘야 한다.
 Fragment 안에 있는 정보를 Adapter를 이용해서 Activity로 보내는 것이 어려웠다.
 Activity에 내가 만든 콜백 리스너를 달고 Adapter를 설정할 때 그 Fragment를 포함하는 Activity의 Context를 넘겨줘서
 아이템 클릭시 Activity 콜백 리스너가 반응을 하게끔 만들어서 데이터를 처리하였다.
 ~~~
 
 파일을 보내야 하는데 다른 데이터(파일 제목, 과목 이름, 교수님 이름 등등)들도 같이 보내야 한다.
 ~~~
 서버에 파일을 보내려면 HTTP 헤더에 데이터 타입을 명시해야한다. 이 Body의 타입을 명시하는 헤더가 Content-type이다.
 하지만 우리 앱 같은 경우는 데이터 타입이 여러 종류가 있어 딱 한 종류만 헤더에 담지 못하는 상황이다.
 그래서 나온 것이 Multipart 타입이다!
 파일과 함께 여러 타입의 데이터를 보내줄 때 Content-Type에 multitype/form-data로 명시해준다.
 ~~~

코드가 길어진다. 나중에 유지보수를 위해 디자인 패턴을 적용하자
~~~
MVP 패턴과 Observer 패턴 채택!
MVP 패턴이랑 View랑 Model을 완전히 분리한다.
View와 Model의 의존성이 높아지면 나중에 유지보수 하기가 어려워진다.
어떻게 분리하는가?
Presenter이라는 중재해주는 클래스를 만들어줬다.
Presenter에서 어떤 메소드를 호출하면 Model에서 일을 시켜 그 결과가 다시 Presenter에게 오고 그 결과를 다시 View에 뿌려준다.
View와 Presenter은 인터페이스로 만들어져있다.
순서대로 흐름을 설명하자면
Activity 혹은 Fragment(View)에서 View 인터페이스를 상속받는다.
Presenter에서 어떤 메소드가 실행한다. 그럼 그 메소드가 실행되고 나서 callback으로 이용해 view에 어떤 처리를 한다.
여기서는 주로 데이터가 변하면 뷰에게 실시간으로 바꾸기 위해 MVP 패턴 및 Observer 패턴을 이용하였다.
조금 말로 설명하기 힘들다. 코드를 보면 바로 이해할 수 있을 것이다.
실시간으로 변할 수 있는 데이터를 처리하기엔 Observer 패턴이 참 좋았다.
데이터가 변하면 관찰하다가 바뀌면 View를 업데이트
Observer 패턴을 적용하지 않았다가 null 데이터 참조로 ANR을 많이 경험했었다. 
~~~
