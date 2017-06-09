package kr.ac.jejunu.repository;

import kr.ac.jejunu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by blue on 2017-06-08.
 */
public interface UserDao extends JpaRepository<User,Long>{
    User findUserByEmail(String email);
}
