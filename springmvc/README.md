### 스프링 MVC 1편 - 백엔드 웹 개발 핵심 기술 (2)

- 스프링 부트 스타터(https://start.spring.io/)
- Project: Gradle - Groovy Project
- 사용 기능: web, thymeleaf, lombok
- groupId: hello artifactId: springmvc
- Spring Boot : 2.7.8
- java : 11
- packaging : Jar
  - Jar를 사용하면 항상 내장 서버(톰캣등)를 사용하고, webapp 경로도 사용하지 않습니다. 내장 서버 사용에
최적화 되어 있는 기능입니다. 최근에는 주로 이 방식을 사용합니다.
  - War를 사용하면 내장 서버도 사용가능 하지만, 주로 외부 서버에 배포하는 목적으로 사용합니다.
