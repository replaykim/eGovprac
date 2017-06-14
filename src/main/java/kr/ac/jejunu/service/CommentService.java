package kr.ac.jejunu.service;

import kr.ac.jejunu.entity.Comment;
import kr.ac.jejunu.repository.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by replay on 2017. 6. 9..
 */
@Service
public class CommentService {

    @Autowired
    CommentDao commentDao;

    public List<Comment> findAllByContentNo(Long contentNo){
        return commentDao.findByContentNo(contentNo);
    }

    public void save(Comment comment) {
        commentDao.save(comment);
    }
}
