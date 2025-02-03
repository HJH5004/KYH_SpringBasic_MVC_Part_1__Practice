package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    //request에 body를 담아와서 전달바등ㅁ
    @PostMapping("/request-body-string-v1")
    public void  requestBodyStrung(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletInputStream inputStream = request.getInputStream();
        String s = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("message = {}", s);

        response.getWriter().write("ok");

    }

    //파라미터를 inpuetStream을 바로 받아와서 사용한다.
    @PostMapping("/request-body-string-v2")
    public void  requestBodyStrung2(InputStream inputStream, Writer writer) throws IOException {

        String s = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("message = {}", s);

        writer.write("ok");

    }

    //파라미터를 Http 엔티티의 값들을 직접 받아와서 전달한다
    @PostMapping("/request-body-string-v3")
    public HttpEntity  requestBodyStrung3(RequestEntity<String> httpEntity) throws IOException {

        String body = httpEntity.getBody();
        HttpHeaders headers = httpEntity.getHeaders();
        log.info("message = {}", body);

        return new HttpEntity<>("ok");
    }
    //요청 파라미터와 message body는 서로 다른 것이다!!!
    //요청 파라미터는 get에 쿼리 파라미터가 오거나  post에서 form에 있는 데이터가 오는 값을 말하는 것이다.




    //body의 String 값을 바로 갖고 온다.
    //어노테이션을 활용하여 bodyMessage에서 String 값을 그대로 가지고 온다.
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String  requestBodyStrung4(@RequestBody String messageBody) throws IOException {

        log.info("message = {}", messageBody);

        return "ok";
    }

}
