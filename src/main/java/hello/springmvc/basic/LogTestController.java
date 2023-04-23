package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @Controller 어노테이션 쓸 때는 return 값이 view 이름을 반환함.
// @RestController 어노테이션은 return 값이 바로 반환 - http message body에 바로 입력한다.
@Slf4j
@RestController
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String LogTest() {
        String name = "Spring";

        // 이전에 하던 방식
        System.out.println("name = " + name);

        // 로거를 사용하는 방식
        log.trace("trace log = {}", name);
        log.debug("debug log = {}", name);
        log.info("info log = {}", name);
        log.warn("warn log = {}", name);
        log.error("error log = {}", name);

        return "ok";
    }
}
