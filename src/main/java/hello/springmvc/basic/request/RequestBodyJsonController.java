package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    // body의 값을 json으로 받아온다,
    // Request 값에서 데이터를 받아와서 전달한다
    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletInputStream inputStream = req.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info(messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());


    }

    // body의 값을 json으로 받아온다,
    // RequestBody에서 값을 받아 가지고 온다.
    @PostMapping("/request-body-json-v2")
    public void requestBodyJsonV2(@RequestBody String messageBody) throws IOException {

        log.info(messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());


    }

    // body의 값을 json으로 받아온다,
    //Model에 값을 직접 받아와서 맵핑해줄 수 있다. 그러나 RequestBody을 생략하면 안됨! 생략하면 @ModelAttribute가 되어버려서 값을 받아올 수가 없음
    @PostMapping("/request-body-json-v3")
    public void requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
    }

    // raw Body와 동일하게 HTtpEntity를 통해서 받아올 수 있음
    @PostMapping("/request-body-json-v4")
    public void requestBodyJsonV4(HttpEntity<HelloData> helloData) throws IOException {
        HelloData body = helloData.getBody();
        log.info("username = {}, age = {}", body.getUsername(), body.getAge());
    }



    // 살짝 번외
    // response에 model을 담아서 return 해줄수도 있다.
    // responsebody에 model을 넣어서 return 하면 json으로 모델을 생성하여 return 해준다.
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) throws IOException {
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());

        return helloData;
    }
}
