package kr.ac.jejunu.entity;

import javax.persistence.*;

/**
 * Created by replay on 2017. 6. 13..
 */

@Entity
public class Friend {
    @Id
    @GeneratedValue
    private Long no;
    @ManyToOne
    @JoinColumn(name = "friend_no",  referencedColumnName = "no")
    private User friend1No;

    @ManyToOne
    @JoinColumn(name = "friend2_no",  referencedColumnName = "no")
    private User friend2No;

    @Enumerated(EnumType.STRING)
    @GeneratedValue
    @Column(name = "relation",columnDefinition = "ENUM DEFAULT half")
    private FriendRelation friendRelation=FriendRelation.half;


    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public User getFriend1No() {
        return friend1No;
    }

    public void setFriend1No(User friend1No) {
        this.friend1No = friend1No;
    }

    public User getFriend2No() {
        return friend2No;
    }

    public void setFriend2No(User friend2No) {
        this.friend2No = friend2No;
    }

    public FriendRelation getFriendRelation() {
        return friendRelation;
    }

    public void setFriendRelation(FriendRelation friendRelation) {
        this.friendRelation = friendRelation;
    }
}
