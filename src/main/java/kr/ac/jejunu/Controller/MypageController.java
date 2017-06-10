package kr.ac.jejunu.Controller;

import kr.ac.jejunu.entity.User;
import kr.ac.jejunu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by replay on 2017. 6. 9..
 */
@Controller
public class MypageController {
    @Autowired
    UserService userService;

    @RequestMapping("/mypage")
    public String viewMypage(HttpServletRequest request, ModelMap modelMap){
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        User mypageUser = userService.findOneByEmail(email);

        modelMap.addAttribute("user", mypageUser);
        return "mypage";
    }

    @GetMapping("/others/{no}")
    public String viewOthersPage(@PathVariable String no, ModelMap modelmap)  {
        Long id = Long.valueOf(no);
        User user = userService.findOneById(id);
        modelmap.addAttribute("user", user);
        return "others";
    }
}
