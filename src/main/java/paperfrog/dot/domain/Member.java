package paperfrog.dot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Member {
    @Id @GeneratedValue
    private Long id;
    private String nickname;

    private String loginId;
    private String password;

    public Member(String nickname) {
        this.nickname = nickname;
    }

    public Member(MemberSaveForm memberSaveForm){
        this.loginId=memberSaveForm.getLoginId();
        this.nickname=memberSaveForm.getNickname();
        this.password=memberSaveForm.getPassword();
    }
    public Member() {

    }
}
