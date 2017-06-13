package kr.ac.jejunu;

import kr.ac.jejunu.entity.FriendRelation;
import kr.ac.jejunu.service.FriendService;
import kr.ac.jejunu.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by replay on 2017. 6. 13..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class FriendServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    FriendService friendService;

    @Test
    public void getRelationTest(){
        Long useNo =1l;
        Long use2No =4l;
        Long use3No =6l;
        Long use4No =5l;


        FriendRelation relation1 = friendService.getRelation(useNo,use2No);
        FriendRelation relation2 = friendService.getRelation(useNo,use3No);
        FriendRelation relation3 = friendService.getRelation(use2No,use3No);

        FriendRelation relation4 = friendService.getRelation(use2No,useNo);
        FriendRelation relation5 = friendService.getRelation(use3No,useNo);
        FriendRelation relation6 = friendService.getRelation(use3No,use2No);

        FriendRelation relation7 = friendService.getRelation(useNo,use4No);
        FriendRelation relation8 = friendService.getRelation(use4No,useNo);

        assertThat(relation1, is(FriendRelation.valueOf("full")));
        assertThat(relation2, is(nullValue()));
        assertThat(relation3, is(nullValue()));
        assertThat(relation4, is(FriendRelation.valueOf("full")));
        assertThat(relation5, is(nullValue()));
        assertThat(relation6, is(nullValue()));
        assertThat(relation7, is(FriendRelation.valueOf("half")));
        assertThat(relation8, is(FriendRelation.valueOf("half")));


    }
}
