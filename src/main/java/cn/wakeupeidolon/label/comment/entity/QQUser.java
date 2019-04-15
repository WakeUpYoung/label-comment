package cn.wakeupeidolon.label.comment.entity;

import javax.persistence.*;

/**
 * @author Wang Yu
 */
@Entity
@Table(name = "tb_qq")
public class QQUser implements BaseBean<Long>{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "figureurl_qq_small")
    private String figureurlQqSmall;
    @Column(name = "figureurl_qq_big")
    private String figureurlQqBig;
    @Column(name = "open_id")
    private String openId;
    @Column(name = "user_id")
    private String userId;
    
    @Override
    public Long getId() {
        return id;
    }
    
    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public String getFigureurlQqSmall() {
        return figureurlQqSmall;
    }
    
    public void setFigureurlQqSmall(String figureurlQqSmall) {
        this.figureurlQqSmall = figureurlQqSmall;
    }
    
    public String getFigureurlQqBig() {
        return figureurlQqBig;
    }
    
    public void setFigureurlQqBig(String figureurlQqBig) {
        this.figureurlQqBig = figureurlQqBig;
    }
    
    public String getOpenId() {
        return openId;
    }
    
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
