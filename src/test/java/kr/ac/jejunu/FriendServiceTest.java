package kr.ac.jejunu;

import kr.ac.jejunu.entity.Friend;
import kr.ac.jejunu.entity.FriendRelation;
import kr.ac.jejunu.entity.User;
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


        FriendRelation relation1 = friendService.getRelation(useNo,use2No).getFriendRelation();
        FriendRelation relation2 = friendService.getRelation(useNo,use3No).getFriendRelation();
        FriendRelation relation3 = friendService.getRelation(use2No,use3No).getFriendRelation();

        FriendRelation relation4 = friendService.getRelation(use2No,useNo).getFriendRelation();
        FriendRelation relation5 = friendService.getRelation(use3No,useNo).getFriendRelation();
        FriendRelation relation6 = friendService.getRelation(use3No,use2No).getFriendRelation();

        FriendRelation relation7 = friendService.getRelation(useNo,use4No).getFriendRelation();
        FriendRelation relation8 = friendService.getRelation(use4No,useNo).getFriendRelation();

        assertThat(relation1, is(FriendRelation.valueOf("full")));
        assertThat(relation2, is(nullValue()));
        assertThat(relation3, is(nullValue()));
        assertThat(relation4, is(FriendRelation.valueOf("full")));
        assertThat(relation5, is(nullValue()));
        assertThat(relation6, is(nullValue()));
        assertThat(relation7, is(FriendRelation.valueOf("half")));
        assertThat(relation8, is(FriendRelation.valueOf("half")));


    }

    @Test
    public void requestFriendTest(){
        Long userNo =1l;
        Long userNo2 =6l;


        //가짜 객체로 잡아야함;
        User user = userService.findOneById(userNo);
        User user2 = userService.findOneById(userNo2);

        Friend friend = new Friend();
        friend.setFriend1No(user);
        friend.setFriend2No(user2);

        friendService.requestFriend(friend);

        FriendRelation relation = friendService.getRelation(userNo,userNo2).getFriendRelation();

        assertThat(relation, is(FriendRelation.valueOf("half")));
    }

    @Test
    public void confirmRequestFriendTest(){
        Long userNo =1l;
        Long userNo2 =6l;

        //make request
        User user = userService.findOneById(userNo);
        User user2 = userService.findOneById(userNo2);

        Friend friend = new Friend();
        friend.setFriend1No(user);
        friend.setFriend2No(user2);

        friendService.requestFriend(friend);

        //make confirm
        Friend requstFriend = friendService.findRequset(user.getNo(), user2.getNo());

        requstFriend.setFriendRelation(FriendRelation.full);
        friendService.confirmFriend(requstFriend);

        FriendRelation relation = friendService.getRelation(userNo,userNo2).getFriendRelation();

        assertThat(relation, is(FriendRelation.valueOf("full")));
    }
}
