package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 컴포넌트 스캔의 대상
// RequestMappingHandlerMapping 의 대상이 된다.
@Controller
public class SpringMemberFormControllerV1 {

    // 해당 URL이 호출되면 이 메서드가 호출된다.
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        // 논리 view name
        // application.properties에 등록해둠
        return new ModelAndView("new-form");
    }
}