package kr.ac.jejunu.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by replay on 2017. 6. 7..
 */
@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long no;
    @ManyToOne
    @JoinColumn(name = "user_no",  referencedColumnName = "no")
    private User user;
    @ManyToOne
    @JoinColumn(name = "content_no",  referencedColumnName = "no")
    private Content content;
    private String text;
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

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }
}
