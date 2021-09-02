package paperfrog.dot.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import paperfrog.dot.domain.ConfirmationToken;
import paperfrog.dot.domain.Member;
import paperfrog.dot.domain.MemberSaveForm;
import paperfrog.dot.repository.MemberRepository;
import paperfrog.dot.web.MemberValidator;
import java.util.List;
/**
 * 회원가입, 로그인 처리를 하는 서비스입니다.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberValidator memberValidator;
    private final ConfirmationTokenService confirmationTokenService;
    public BindingResult join(MemberSaveForm memberForm, BindingResult bindingResult){
        memberValidator.validate(memberForm,bindingResult);
        if(bindingResult.hasErrors())
            return bindingResult;
        Member saveMember = new Member(memberForm);
        Long id=memberRepository.save(saveMember);
        confirmationTokenService.createEmailConfirmationToken(id,saveMember.getEmail());
        return bindingResult;
    }
    public void join(MemberSaveForm memberForm){
        memberValidator.validate(memberForm,null);
        Member saveMember = new Member(memberForm);
        Long id=memberRepository.save(saveMember);
        confirmationTokenService.createEmailConfirmationToken(id,saveMember.getEmail());
    }
    public List<Member> findAll(){
        return memberRepository.findAll();
    }
    public Member findById(Long id){
        return memberRepository.findById(id);
    }
//    public Member findByNickname(String nickname){
//        return  memberRepository.findByNickname(nickname);
//    }
    /**
     로그인 실패 -> return null
     */
    public Member login(String loginId,String password){
        return  memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
    public void confirmEmail(String tokenId) {
        ConfirmationToken findConfirmationToken = confirmationTokenService.findById(tokenId);
        confirmationTokenService.expireToken(tokenId);
        memberRepository.emailVerified(findConfirmationToken.getUserId());
    }
}
