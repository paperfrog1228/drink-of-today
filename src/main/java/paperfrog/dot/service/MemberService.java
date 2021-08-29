package paperfrog.dot.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paperfrog.dot.domain.Member;
import paperfrog.dot.repository.MemberRepository;
import java.util.List;
/**
 * 회원가입, 로그인 처리를 하는 서비스입니다.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public Long join(Member member){
        memberRepository.save(member);
        return member.getId();
    }
    public List<Member> findAll(){
        return memberRepository.findAll();
    }
    public Member findById(Long id){
        return memberRepository.findById(id);
    }
    /**
     로그인 실패 -> return null
     */
    public Member login(String loginId,String password){
        return  memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
