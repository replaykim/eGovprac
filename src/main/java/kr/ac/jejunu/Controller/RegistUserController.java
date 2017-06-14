package kr.ac.jejunu.Controller;

import kr.ac.jejunu.entity.User;
import kr.ac.jejunu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by replay on 2017. 6. 13..
 */
@Controller
public class RegistUserController {
    @Autowired
    UserService userService;

    @RequestMapping("registuser")
    public String registUser() {
        return "registuser";
    }

    @RequestMapping("saveuser")
    public String saveUser(@RequestParam("file") MultipartFile file, User user, ModelMap modelMap) {


        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("src/main/resources/static/photoes/" + file.getOriginalFilename()));
            BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream);
            user.setPhoto("/photoes/" + file.getOriginalFilename());

            outputStream.write(file.getBytes());
            outputStream.close();
        } catch (IOException e) {
            //default
            user.setPhoto("/photoes/anonymous.png");
        }

        userService.save(user);

        return "redirect:/";
    }
}
