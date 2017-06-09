package kr.ac.jejunu.service;

import kr.ac.jejunu.repository.UserDao;
import kr.ac.jejunu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by blue on 2017-06-08.
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User findOneByEmail(String Email){

        return userDao.findUserByEmail(Email);
    }

    public User save(User user) {
        return userDao.save(user);
    }

    public void update(User user) {
        userDao.save(user);
    }
}
