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

    public FriendRelation getRelation(Long userNo, Long userNo2) {

        Friend friend = friendDao.findByFriendRelation(userNo,userNo2);
        Friend friend2 = friendDao.findByFriendRelation(userNo2,userNo);

        if (friend != null){
            return friend.getFriendRelation();
        }else if (friend2 != null){
            return friend2.getFriendRelation();
        }
        return null;
    }
}
