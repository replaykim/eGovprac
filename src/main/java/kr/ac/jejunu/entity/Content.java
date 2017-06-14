package kr.ac.jejunu.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by replay on 2017. 6. 5..
 */
@Entity
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private Long no;
    @ManyToOne
    @JoinColumn(name = "user_no",  referencedColumnName = "no")
    private User user;
    private String contents;
    @ManyToOne
    @JoinColumn(name = "wall",  referencedColumnName = "no")
    private User wall = user;
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name = "content_no")
    private List<Comment> comments;
    @Column(name = "`like`")
    private Long like =0l;
    private Long dislike=0l;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable=false)
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

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public User getWall() {
        return wall;
    }

    public void setWall(User wall) {
        this.wall = wall;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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
