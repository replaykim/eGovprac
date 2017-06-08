package kr.ac.jejunu.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by replay on 2017. 6. 5..
 */
@Entity
public class Content {
    @Id
    @GeneratedValue
    private Long no;
    @ManyToOne
    @JoinColumn(name = "user_no",  referencedColumnName = "no")
    private User user;
    private String content;
    private Long like;
    private Long dislike;
    private Date registDate;

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getLike() {
        return like;
    }

    public void setLike(Long like) {
        this.like = like;
    }

    public Long getDislike() {
        return dislike;
    }

    public void setDislike(Long dislike) {
        this.dislike = dislike;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }
}
