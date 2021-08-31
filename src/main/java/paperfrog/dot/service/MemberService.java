package paperfrog.dot.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import paperfrog.dot.domain.Member;
import paperfrog.dot.domain.MemberSaveForm;
import paperfrog.dot.repository.MemberRepository;
import paperfrog.dot.web.MemberValidator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 회원가입, 로그인 처리를 하는 서비스입니다.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberValidator memberValidator;
    public BindingResult join(MemberSaveForm memberForm, BindingResult bindingResult){
        memberValidator.validate(memberForm,bindingResult);
        if(bindingResult.hasErrors())
            return bindingResult;
        Member saveMember = new Member(memberForm);
        memberRepository.save(saveMember);
        return bindingResult;
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
    //검증을 여기서 하는게 맞을까..?
    private Boolean patternCheck(String str){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher matcher=pattern.matcher(str);
        return matcher.find();
    }
}
