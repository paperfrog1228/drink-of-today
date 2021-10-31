package paperfrog.dot.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paperfrog.dot.domain.ConfirmationToken;
import paperfrog.dot.domain.Member;
import paperfrog.dot.domain.MemberSaveForm;
import paperfrog.dot.etc.LineAPI;
import paperfrog.dot.repository.MemberRepository;
import paperfrog.dot.web.EncryptManager;
import paperfrog.dot.web.MemberValidator;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

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
    private final EncryptManager encryptManager;
    private final LineAPI lineAPI;
    public Long join(MemberSaveForm memberForm) throws NoSuchAlgorithmException {
        memberForm.setPassword(encryptPassword(memberForm.getPassword()));
        Member saveMember = new Member(memberForm);
        Long id=memberRepository.save(saveMember);
        confirmationTokenService.createEmailConfirmationToken(id,saveMember.getEmail());
        return id;
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
    public Member login(String loginId,String password) throws NoSuchAlgorithmException, IOException {
        password=encryptManager.encrypt(password);
        String finalPassword = password;
        if(loginId=="guest")
            lineAPI.sendRequest("게스트 로그인");
        Member member=memberRepository.findByLoginId(loginId);
        if(member==null) return null;
        if(member.getPassword().equals(finalPassword))
            return member;
        else return null;
    }
    public void confirmEmail(String tokenId) {
        ConfirmationToken findConfirmationToken = confirmationTokenService.findById(tokenId);
        confirmationTokenService.expireToken(tokenId);
        memberRepository.emailVerified(findConfirmationToken.getUserId());
    }
    private String encryptPassword(String password) throws NoSuchAlgorithmException {
        return encryptManager.encrypt(password);
    }
}
