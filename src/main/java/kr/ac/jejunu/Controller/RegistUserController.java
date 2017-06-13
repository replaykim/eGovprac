package kr.ac.jejunu.Controller;

import kr.ac.jejunu.entity.User;
import kr.ac.jejunu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by replay on 2017. 6. 13..
 */
@Controller
public class RegistUserController {
    @Autowired
    UserService userService;

    @RequestMapping("registuser")
    public String registUser(){
        return "registuser";
    }

    @RequestMapping("saveuser")
    public String saveUser(User user, ModelMap modelMap){
        userService.save(user);

        modelMap.addAttribute("ok","완료");

        return "redirect:/";
    }
}
