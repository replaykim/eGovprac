package kr.ac.jejunu.repository;

import kr.ac.jejunu.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * Created by replay on 2017. 6. 13..
 */
public interface FriendDao extends JpaRepository<Friend, Long>{
    @Query("select f from Friend f where f.friend1No.no = ?1  AND f.friend2No.no = ?2")
    Friend findByFriendRelation(Long friendNo, Long friendNo2);

}
