package kr.ac.jejunu.repository;

import kr.ac.jejunu.entity.Comment;
import kr.ac.jejunu.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by replay on 2017. 6. 9..
 */

public interface CommentDao extends JpaRepository<Comment, Long>{
    List<Comment> findByContentNo(Long contentNo);
}
