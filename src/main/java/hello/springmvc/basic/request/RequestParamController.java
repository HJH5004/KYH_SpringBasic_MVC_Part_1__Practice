package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    //Http 통신으로 부터 필요한 파라미터 값을 어떻게 가지고 오는가?


    //HttpServlerRequest를 통한 param값 호출
    @RequestMapping("/request-param-v1")
    public void requestParamV1( HttpServletRequest req, HttpServletResponse resp ) throws IOException {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        log.info("username:{}, age:{}", username, age);

        resp.getWriter().write("ok");

    }

    //RequestParam값을 통한 호출
    //ResponseBody를 통한 getWriter() 생략
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String username, @RequestParam("age") int age) throws IOException {

        log.info("username:{}, age:{}", username, age);

        return "ok";
    }

    //RequestParam값을 통한 호출 - 맵핑 value를 따로 선언하지 않고 설정
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username, @RequestParam int age) throws IOException {

        log.info("username:{}, age:{}", username, age);

        return "ok";
    }

    //RequestParam 마저 사용하지 않는다. Parameter의 Key값이 method 파라미터의 변수명과 동일하면 자동으로 들어간다.
    //이렇게 쓸 수는 있으나 Spring이 익숙하지 않은 사람에게는 익숙하지 않을수도 있음.
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) throws IOException {

        log.info("username:{}, age:{}", username, age);

        return "ok";
    }

    //RequestParam 심화.
    //required 여부를 통한 필수 여부 벨리데이션. 만약 required가 false로 들어오게 된다면 해당 파라미터 값이 없어도 exception을 발생시키지 않는다.
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username, @RequestParam(required = false) Integer age) throws IOException {

        log.info("username:{}, age:{}", username, age);

        return "ok";
    }

    //RequestParam 심화
    //만약 해당 값이 없으면 defalutValue를 설정해주어 해당 값이 들어오도록 해준다.
    //이 메소드의 특징은 해당 값이 공란 (ex username=) 같은 케이스에서도 대응해준다.
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(required = true, defaultValue = "guest") String username, @RequestParam(required = false, defaultValue = "-1") Integer age) throws IOException {

        log.info("username:{}, age:{}", username, age);

        return "ok";
    }

    //RequestParam심화
    //Param의 값을 Map<String, Object> 로 받아올 수 있도록 한다.
    //이 떄 Key에 해당 하는 값은 파라미터의 이름으로 들어오게 됨
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMAp) throws IOException {

        Object username = paramMAp.get("username");
        Object age = paramMAp.get("age");

        log.info("username:{}, age:{}", username, age);

        return "ok";
    }

    /*========================Model값 생성============*/


    //v1 - ModelAttribute 을 활용
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) throws IOException {

        log.info("helloData:{}", helloData);

        return "ok";
    }

    //v1 - ModelAttribute 마저 빼버릴 수 있다...
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2( HelloData helloData) throws IOException {

        log.info("helloData:{}", helloData);

        return "ok";
    }
}
