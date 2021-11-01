package paperfrog.dot.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import paperfrog.annotation.NoSpace;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//검증 때문에 멤버 객체랑 별도로 분리해놓음
@Data
public class MemberSaveForm {
    @Size(max=10,min=2)
    @NotBlank
    private String nickname;
    @Size(min=4, max=20)
    @NoSpace
    @NotBlank
    private String loginId;
    @NoSpace
    @NotBlank
    @Size(min=6, max=20)
    private String password;
    @Email
    @NotBlank
    private String email;
    @Email
    @NotBlank
    private String emailConfirm;
}
