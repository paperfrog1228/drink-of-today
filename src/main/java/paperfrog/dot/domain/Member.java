package paperfrog.dot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Member {
    @Id @GeneratedValue
    private Long id;
    private String nickname;
    private String email;
    private String loginId;
    @Enumerated(EnumType.STRING)
    private MemberGrade memberGrade=MemberGrade.NORMAL;
    private String password;
    private boolean emailAuth;
    public Member(String nickname) {
        this.nickname = nickname;
    }

    public Member(MemberSaveForm memberSaveForm){
        this.loginId=memberSaveForm.getLoginId();
        this.nickname=memberSaveForm.getNickname();
        this.password=memberSaveForm.getPassword();
        this.email=memberSaveForm.getEmail();
    }
    public Member() {

    }
}
