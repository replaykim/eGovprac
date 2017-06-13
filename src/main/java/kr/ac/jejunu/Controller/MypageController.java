package kr.ac.jejunu.Controller;

import kr.ac.jejunu.entity.Content;
import kr.ac.jejunu.entity.User;
import kr.ac.jejunu.service.ContentService;
import kr.ac.jejunu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by replay on 2017. 6. 9..
 */
@Controller
public class MypageController {
    @Autowired
    UserService userService;

    @Autowired
    ContentService contentService;

    @RequestMapping("/mypage")
    public String viewMypage(HttpServletRequest request, ModelMap modelMap){
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        User mypageUser = userService.findOneByEmail(email);


        modelMap.addAttribute("user", mypageUser);
        return "mypage";
    }


    @PostMapping("savecontent")
    public String saveContent(HttpServletRequest request, @RequestParam(name = "content")String contents){
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        User mypageUser = userService.findOneByEmail(email);

        Content content = new Content();
        content.setContents(contents);
        content.setUser(mypageUser);

        contentService.save(content);

        return "forward:/mypage";
    }
}
