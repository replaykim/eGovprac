package kr.ac.jejunu.Controller;

import kr.ac.jejunu.entity.Comment;
import kr.ac.jejunu.entity.Content;
import kr.ac.jejunu.entity.User;
import kr.ac.jejunu.service.CommentService;
import kr.ac.jejunu.service.ContentService;
import kr.ac.jejunu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * Created by replay on 2017. 6. 9..
 */

@Controller
public class BoardController {
    @Autowired
    ContentService contentService;
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String mainPage(ModelMap modelMap){
        HashMap hashMap = new HashMap();
        List<Content> contents = contentService.findAll();

        modelMap.addAttribute("result",contents);
        return  "index";
    }

}
