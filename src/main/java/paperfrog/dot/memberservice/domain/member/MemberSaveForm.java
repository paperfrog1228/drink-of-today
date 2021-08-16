package paperfrog.dot.memberservice.domain.member;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import paperfrog.annotation.NoSpace;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//검증 때문에 멤버 객체랑 별도로 분리해놓음
@Data
public class MemberSaveForm {
    @NotBlank
    private String nickname;
    @Size(min=4, max=12)
    @NoSpace
    @NotBlank
    private String loginId;
    @NoSpace
    @Size(min=6, max=12)
    @NotBlank
    private String password;

}
