package kr.ac.jejunu.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by blue on 2017-06-08.
 */

@Controller
public class LoginController {
    @RequestMapping("/")
    public String indexControl(){
        return "index";
    }

}
