package paperfrog.dot.memberservice.domain.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import paperfrog.dot.memberservice.domain.member.Member;
import paperfrog.dot.memberservice.domain.member.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;
    /**
      로그인 실패 -> return null
     */
    public Member login(String loginId,String password){
        return  memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
