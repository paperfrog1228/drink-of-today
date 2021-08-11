package paperfrog.dot.memberservice.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    private Long id;
    private String nickname;

    public Member(String nickname) {
        this.nickname = nickname;
    }
}
