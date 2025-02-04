package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    //정적 리소스 이동
    //ModelAndView 전달
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        //정적 리소스 위치와 데이터를 전달
        ModelAndView modelAndView = new ModelAndView("response/hello").addObject("data", "Hello World");

        return modelAndView;

    }

    //정적 리소스 이동
    //@Controller에서 String을 return하면 리소스의 경로를 리턴한다!
    //이게 그나마 많이 쓰임!
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){
        model.addAttribute("data", "Hello World");

        return "response/hello";

    }

    //정적 리소스 이동
    //컨트롤러의 경로와 리소스의 경로가 동일하면 바로 view를 불러아주는데 별로 권장하지 않는다.
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model){
        model.addAttribute("data", "Hello World");
    }

}
