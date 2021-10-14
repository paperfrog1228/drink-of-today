package paperfrog.dot.integrationTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import paperfrog.dot.IntegrationTest;
import paperfrog.dot.domain.MemberSaveForm;
import paperfrog.dot.repository.MemberRepository;
import paperfrog.dot.service.MemberService;
import paperfrog.dot.web.EncryptManager;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class memberTest extends IntegrationTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EncryptManager encryptManager;
    String loginID="loginID";
    String password="ttest!#42s";

    MemberSaveForm memberSaveForm;
    @BeforeEach
    public void createMember(){
        memberSaveForm =new MemberSaveForm();
        memberSaveForm.setLoginId(loginID);
        memberSaveForm.setPassword(password);
        memberSaveForm.setEmail("paperfrog@naver.com");
    }
    @AfterEach
    public void deleteRepository(){
        memberRepository.deleteAll();
    }
    @Test
    @DisplayName("로그인 테스트")
    public void login_test() throws NoSuchAlgorithmException, IOException {
        //given
        Long expectId=memberService.join(memberSaveForm);
        //when
        Long resultId=memberService.login(loginID,password).getId();
        //then
        Assertions.assertThat(expectId).isEqualTo(resultId);
    }
    @Test
    @DisplayName("패스워드 암호화 테스트")
    public void encrypt_test() throws NoSuchAlgorithmException, IOException {
        //given
        String expectPassword=encryptManager.encrypt(password);
        memberService.join(memberSaveForm);
        //when
        String resultPassword=memberService.login(loginID,password).getPassword();
        //then
        if(resultPassword==null)
        System.out.println("resultPassword is null.");
        Assertions.assertThat(expectPassword).isEqualTo(resultPassword);
    }

}
