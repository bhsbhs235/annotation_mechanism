# Annotation Mechnism 

애노테션 프로세서는 소스코드 레벨에서 소스코드에 붙어있는 애노테이션 정보들을 읽어서 컴파일러가 컴파일 할 때 새로운 소스코드를 생성하거나 기존의 코드를 바꾸는 기술 클래스, 바이트코드, 리소스파일도 생성이 가능하다.

ex) @Getter, @Setter 애노테이션을 추가 했을 때 소스코드에서는 보이지 않지만 컴파일 한 바이트코드에서 보면 set,get이 자동으로 생성되어 있다.

## 프로젝트 설명

해당 프로젝트를 배포한 후 임포트하면 Moja 인터페이스를 구현한 Class Name "MagicMoja"의 클래스가 생성되고 "Hello World"를 리턴하는 pullOut()함수가 자동으로 생성됩니다.