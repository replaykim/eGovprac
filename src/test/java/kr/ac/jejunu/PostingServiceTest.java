package kr.ac.jejunu;

import kr.ac.jejunu.entity.Content;
import kr.ac.jejunu.service.ContentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by blue on 2017-06-08.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PostingServiceTest {

    private Logger logger = LoggerFactory.getLogger(PostingServiceTest.class);
    @Autowired
    private ContentService contentService;

    @Test
    public void findOne(){
        String content="임시1";
        Long like = 1l;
        Long userNo = 1l;

        Content content1 = contentService.findOne(userNo);

        assertThat(content,is(content1.getContent()));
        assertThat(like,is(content1.getLike()));
    }
    @Test
    public void getContentsByUserId(){
        Long userNo = 1l;

        List<Content> contents = contentService.findAllByUser(userNo);

        for (int i =0; i<contents.size();i++){
            logger.info("**************  get list  ******************");
            logger.info(contents.get(i).getContent());
            assertThat(userNo,is(contents.get(i).getUser().getNo()));
        }
    }
}
