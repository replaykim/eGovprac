package kr.ac.jejunu.Controller;

import kr.ac.jejunu.entity.Comment;
import kr.ac.jejunu.entity.Content;
import kr.ac.jejunu.entity.User;
import kr.ac.jejunu.service.CommentService;
import kr.ac.jejunu.service.ContentService;
import kr.ac.jejunu.service.UserService;
import kr.ac.jejunu.util.DateUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * Created by replay on 2017. 6. 9..
 */

@Controller
public class BoardController {
    private Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    ContentService contentService;
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String mainPage(ModelMap modelMap, HttpServletRequest request){
        PageRequest pageRequest = new PageRequest(0, 100, new Sort(Sort.Direction.DESC, "registDate"));
        Page<Content> result = contentService.findAllsort(pageRequest);

        List<Content> contents = result.getContent();
//        List<Content> contents = contentService.findAll();
        modelMap.addAttribute("result",contents);
        modelMap.addAttribute("requestFrom", request.getRequestURI());
        return  "index";
    }

    @PostMapping("/commentsave")
    public String commentSave(Comment comment, HttpServletRequest request, @RequestParam(name = "requestFrom")String fromurl){
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        User mypageUser = userService.findOneByEmail(email);

        comment.setUser(mypageUser);

        commentService.save(comment);

        logger.info(fromurl);

        return "redirect:"+fromurl;
    }

}
