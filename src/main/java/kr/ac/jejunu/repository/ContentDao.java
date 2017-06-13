package kr.ac.jejunu.repository;

import kr.ac.jejunu.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by blue on 2017-06-08.
 */
public interface ContentDao extends JpaRepository<Content,Long>{
//    @Query("select C from Content C join User u where u.no = ?1")
    List<Content> findByUserNo(Long userNo);
}
