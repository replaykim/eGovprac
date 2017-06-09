package kr.ac.jejunu.Controller;

import kr.ac.jejunu.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by blue on 2017-06-08.
 */

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/logincheck")
    public String loginCheck(User user, ModelMap modelMap){
        boolean logincheck = true;

        if (logincheck){
            return "redirect:/";
        }else {
            modelMap.addAttribute("loginerr","무언가 틀렸습니다");
            return "login";
        }
    }

}
