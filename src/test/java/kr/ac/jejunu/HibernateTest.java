package kr.ac.jejunu;

import kr.ac.jejunu.entity.Comment;
import kr.ac.jejunu.entity.User;
import kr.ac.jejunu.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;


import javax.transaction.Transactional;

/**
 * Created by blue on 2017-06-08.
 */


@Transactional
public class HibernateTest {
    @Autowired
    UserService userService;

    private Logger logger = LoggerFactory.getLogger(HibernateTest.class);

    SessionFactory sessionFactory;

    @Before
    public void setup() {
        Configuration configuration = new Configuration().configure("jeju.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Comment.class);

//        configuration.addResource("User.hbm.xml");
//        configuration.addResource("Comment.hbm.xml");
        StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
        sb.applySettings(configuration.getProperties());
        StandardServiceRegistry standardServiceRegistry = sb.build();
        sessionFactory = configuration.buildSessionFactory(standardServiceRegistry);
    }

    @After
    public void finish() {
        sessionFactory.close();
    }

    @Test
    public void getUserTest() {
        String name = "adfaf";
        String password = "1234";
        Long no = 1l;

        Session session = sessionFactory.openSession();
        User user = session.get(User.class, no);
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
//        user.getComments().forEach(comment->{
//            logger.error(comment.getContent());
//        });
        session.close();
    }

    @Test
    public void saveUserTest() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        User user = new User();
        user.setName("testuser");
        user.setPassword("1111");
        Long id = (Long)session.save(user);

        logger.info("**************  get after save(start) ******************");
        User savedUser = session.get(User.class, id);
        logger.info("**************  get after save(end) ******************");
        assertThat(savedUser.getName(), is(user.getName()));
        assertThat(savedUser.getPassword(), is(user.getPassword()));

        session.getTransaction().commit();
        session.close();
    }
}
