package kr.ac.jejunu.service;

import kr.ac.jejunu.repository.ContentDao;
import kr.ac.jejunu.entity.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by blue on 2017-06-08.
 */
@Service
public class ContentService {
    @Autowired
    ContentDao contentDao;

    public List<Content> findAllByUser(Long userNo) {
        return (List<Content>) contentDao.findByUserNo(userNo);
    }

    public Content findOne(Long userNo) {
        return contentDao.findOne(userNo);
    }

    public List<Content> findAll() {
        return contentDao.findAll();
    }

    public Content save(Content content) {
        return contentDao.saveAndFlush(content);
    }
}
