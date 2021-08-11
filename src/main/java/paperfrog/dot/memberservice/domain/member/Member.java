package paperfrog.dot.memberservice.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    private Long id;
    private String nickname;
    private String loginId;
    private String password;
    public Member(String nickname) {
        this.nickname = nickname;
    }
}
