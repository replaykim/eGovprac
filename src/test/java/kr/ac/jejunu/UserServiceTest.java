package kr.ac.jejunu;

import kr.ac.jejunu.entity.User;
import kr.ac.jejunu.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
/**
 * Created by blue on 2017-06-08.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void getUserByEmailTest(){
        String email = "qwer@never.com";
        String name = "adfaf";
        String password = "1234";
        Long no = 1l;

        User getUser = userService.findOneByEmail(email);

        assertThat(email,is(getUser.getEmail()));
        assertThat(name, is(getUser.getName()));
        assertThat(password, is(getUser.getPassword()));
        assertThat(no,is(getUser.getNo()));
    }
    @Test
    public void saveUser(){
        String email = "asfd@naver.com";
        String name = "wewe";
        String password = "12345";

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);

        userService.save(user);

        User getUser = userService.findOneByEmail(email);

        assertThat(email,is(getUser.getEmail()));
        assertThat(name, is(getUser.getName()));
        assertThat(password, is(getUser.getPassword()));
    }
    @Test
    public void changeUser(){
        String email = "asfd@naver.com";
        String name = "wewe";
        String password = "12345";
        String changedName = "mymymymy";
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);

        User savedUser = userService.save(user);

        user.setName(changedName);
        user.setNo(savedUser.getNo());

        userService.update(user);

        User getUser = userService.findOneByEmail(email);

        assertThat(savedUser.getNo(),is(getUser.getNo()));
        assertThat(email,is(getUser.getEmail()));
        assertThat(changedName, is(getUser.getName()));
        assertThat(password, is(getUser.getPassword()));
    }
}
