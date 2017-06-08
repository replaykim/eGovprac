package kr.ac.jejunu.dao;

import kr.ac.jejunu.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by blue on 2017-06-08.
 */
public interface ContentDao extends JpaRepository<Content,Long>{
//    @Query("select c from Content c join User u where u.no = ?1")
    List<Content> findByUserNo(Long userNo);
}
