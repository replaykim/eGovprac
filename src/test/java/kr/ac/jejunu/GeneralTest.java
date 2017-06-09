package kr.ac.jejunu;

import kr.ac.jejunu.entity.Comment;
import kr.ac.jejunu.entity.User;
import kr.ac.jejunu.service.CommentService;
import kr.ac.jejunu.service.ContentService;
import kr.ac.jejunu.service.UserService;
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
 * Created by replay on 2017. 6. 9..
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class GeneralTest {
    private Logger logger = LoggerFactory.getLogger(PostingServiceTest.class);

    @Autowired
    ContentService contentService;
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;

    @Test
    public void getUserCommentByContent(){
        Long contentId = 1l;
        String user1name = "adfaf";
        String user4name = "testuser";

        List<Comment> comments = commentService.findAllByContentNo(contentId);
        User user = contentService.findOne(contentId).getUser();

        for (int i = 0; i <comments.size();i++){
            assertThat(contentId,is(comments.get(i).getContent().getNo()));
            logger.info(comments.get(i).getText());
        }
        assertThat(user1name,is(comments.get(0).getUser().getName()));
        assertThat(user4name,is(comments.get(1).getUser().getName()));

    }
}
