package st.example.spring.rest;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;

@Controller
@EnableAutoConfiguration
public class SampleController {

    @Value("${server.port}")
    String port;

//    @RequestMapping("/")
//    @ResponseBody
    String home() {
        return "Hello World! = " + port;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}
