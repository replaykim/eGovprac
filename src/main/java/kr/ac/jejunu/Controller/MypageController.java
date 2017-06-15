package kr.ac.jejunu.Controller;

import kr.ac.jejunu.entity.Content;
import kr.ac.jejunu.entity.User;
import kr.ac.jejunu.service.ContentService;
import kr.ac.jejunu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

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

//        List<Content> contents = contentService.findAllByWall(mypageUser);

        PageRequest pageRequest = new PageRequest(0, 100, new Sort(Sort.Direction.DESC, "registDate"));
        Page<Content> result = contentService.findByWallNosort(mypageUser,pageRequest);

        List<Content> contents = result.getContent();


        modelMap.addAttribute("requestFrom", request.getRequestURI());
        modelMap.addAttribute("result",contents);
        modelMap.addAttribute("user", mypageUser);
        return "mypage";
    }


    @PostMapping("/savecontentinmy")
    public String saveContent(HttpServletRequest request, @RequestParam(name = "content")String contents){
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        User mypageUser = userService.findOneByEmail(email);

        Content content = new Content();
        content.setContents(contents);
        content.setUser(mypageUser);

        content.setWall(mypageUser);

        contentService.save(content);

        return "redirect:/mypage";
    }

    @RequestMapping("edituser")
    public String editUser(HttpServletRequest request, ModelMap modelMap){
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        User mypageUser = userService.findOneByEmail(email);

        modelMap.addAttribute("user", mypageUser);

        return "edituser";
    }

    @PostMapping("updateuser")
    public String updateUser(HttpServletRequest request, @RequestParam("file") MultipartFile file, User user){
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        User mypageUser = userService.findOneByEmail(email);

        if (!user.getPassword().equals("")){
            mypageUser.setPassword(user.getPassword());
        }

        if (!user.getName().equals("")){
            mypageUser.setName(user.getName());
        }

        if (file != null ){
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(new File("src/main/resources/static/photoes/" + file.getOriginalFilename()));
                BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream);
                user.setPhoto("/photoes/" + file.getOriginalFilename());

                outputStream.write(file.getBytes());
                outputStream.close();
            } catch (IOException e) {
                //default
            }
        }



        userService.update(mypageUser);
        return "redirect:/mypage";
    }
}
