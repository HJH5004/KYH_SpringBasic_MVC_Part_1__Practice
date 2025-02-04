package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
//@Controller
//ResponseBody를 class 레벨에 넣으면 하단에 알아서 들어간다.
//@ResponseBody


//@Controller + @ResponseBOdy = @RestController
@RestController
public class ResponseBodyController {

    //Http 응답에 데이터를 넣는 방법
    //Writer를 활용하여 body에 입력
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse resp) throws IOException {
        resp.getWriter().write("Hello World");
    }

    //ResponseEntity에 입력해서 return
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2(HttpServletResponse resp) throws IOException {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    //ResponseBody 어노테이션 활용
//    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3(HttpServletResponse resp) throws IOException {
        return "ok";
    }

    //json을 활용한 데이터 return
    //ResponseEntity 를 활용한 json flxjs
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1(HttpServletResponse resp) throws IOException {
        HelloData helloData = new HelloData();
        helloData.setUsername("jake");
        helloData.setAge(22);
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    //json을 활용한 데이터 return
    //ResponseBody 를 활용한 json  전달
    // 이 타입을 가장 많이 쓴다. Class에는 @RestCotnroller를 설정하여 전달하는 방식으로 주로 많이 씀
    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() throws IOException {
        HelloData helloData = new HelloData();
        helloData.setUsername("jake");
        helloData.setAge(22);
        return helloData;
    }

}
