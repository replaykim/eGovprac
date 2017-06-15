package kr.ac.jejunu;

import kr.ac.jejunu.entity.Content;
import kr.ac.jejunu.entity.User;
import kr.ac.jejunu.service.ContentService;
import kr.ac.jejunu.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by blue on 2017-06-08.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostingServiceTest {

    private Logger logger = LoggerFactory.getLogger(PostingServiceTest.class);
    @Autowired
    private ContentService contentService;
    @Autowired
    private UserService userService;
    @Test
    public void saveTest(){
        Long userNo = 11l;
        String contents = "asd";
        User user = userService.findOneById(userNo);
        Content content = new Content();

        content.setUser(user);
        content.setContents(contents);

        contentService.save(content);

    }

    @Test
    public void findOne(){
        String content="임시1";
        Long like = 1l;
        Long No = 1l;

        Content content1 = contentService.findOne(No);

        assertThat(content,is(content1.getContents()));
        assertThat(like,is(content1.getLike()));
    }
    @Test
    public void getContentsByUserId(){
        Long userNo = 1l;

        List<Content> contents = contentService.findAllByUser(userNo);

        for (int i =0; i<contents.size();i++){
            logger.info("**************  get list  ******************");
            logger.info(contents.get(i).getContents());
            assertThat(userNo,is(contents.get(i).getUser().getNo()));
        }
    }

    @Test
    public void getCommentList(){
        String content="임시1";
        Long like = 1l;
        Long userNo = 1l;

        List<Content> content1 = contentService.findAll();

        assertThat(2,is(content1.get(0).getComments().size()));
    }
}
