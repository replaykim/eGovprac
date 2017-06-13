package kr.ac.jejunu.service;

import kr.ac.jejunu.entity.Friend;
import kr.ac.jejunu.entity.FriendRelation;
import kr.ac.jejunu.repository.FriendDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by replay on 2017. 6. 13..
 */
@Service
public class FriendService {
    @Autowired
    FriendDao friendDao;

    public Friend getRelation(Long userNo, Long userNo2) {

        Friend friend = friendDao.findByFriendRelation(userNo,userNo2);
        Friend friend2 = friendDao.findByFriendRelation(userNo2,userNo);

        if (friend != null){
            return friend;
        }else if (friend2 != null){
            return friend2;
        }
        return null;
    }

    public void requestFriend(Friend friend) {
        friendDao.save(friend);
    }

    public Friend findRequset(Long no, Long no1) {
        //요청한 사람이 뒤에있다.
        return friendDao.findByFriendRelation(no, no1);
    }

    public void confirmFriend(Friend requstFriend) {
        friendDao.save(requstFriend);
    }
}
