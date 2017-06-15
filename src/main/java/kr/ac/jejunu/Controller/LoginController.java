package kr.ac.jejunu.Controller;

import kr.ac.jejunu.entity.User;
import kr.ac.jejunu.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.security.auth.login.LoginContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by blue on 2017-06-08.
 */

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;


    @RequestMapping("/login")
    public String loginPage(HttpServletRequest request){
        HttpSession session = request.getSession(false);

        if (session != null){
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/logincheck")
    public String loginCheck(User user, ModelMap modelMap, HttpServletRequest request){

        boolean logincheck = loginService.loginCheck(user);

        if (logincheck){
            HttpSession session = request.getSession(true);
            session.setAttribute("email", user.getEmail());

            return "redirect:/";
        }else {
            modelMap.addAttribute("loginerr","무언가 틀렸습니다");
            return "login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();

        return "redirect:/";
    }
}
