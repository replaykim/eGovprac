package kr.ac.jejunu.service;

import kr.ac.jejunu.entity.User;
import kr.ac.jejunu.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by replay on 2017. 6. 9..
 */
@Service
public class LoginService {

    @Autowired
    UserDao userDao;

    public boolean loginCheck(User user){
        boolean result = false;
        String email = user.getEmail();

        User forLoginCheck = userDao.findUserByEmail(email);

        if (forLoginCheck != null){
            if (user.getPassword().equals(forLoginCheck.getPassword())){
                result = true;
            }
        }


        return result;
    }
}
