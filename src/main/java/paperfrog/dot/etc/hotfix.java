package paperfrog.dot.etc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import paperfrog.dot.domain.Member;
import paperfrog.dot.repository.MemberRepository;
import paperfrog.dot.web.EncryptManager;

import javax.annotation.PostConstruct;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
    db 마이그레이션 등 1회성으로 수정할 때 사용하는 클래스.
    수정을 완료 후 다음 커밋 전까지 동작 내용 지우던가 주석처리 해야함.
 **/
@Component
@RequiredArgsConstructor
@Slf4j
public class hotfix {
    private final MemberRepository memberRepository;
    private final EncryptManager encryptManager;
    /**
      2021. 10. 13
    비밀번호 암호화 적용 후
    기존에 있는 비밀번호 암호화 적용함
     **/
//    @PostConstruct
//    private void encryptExistingPassword() throws NoSuchAlgorithmException {
//        List<Member> list=memberRepository.findAll();
//        for(Member m:list){
//            if(m.getPassword().length()<64){
//                m.setPassword(encryptManager.encrypt(m.getPassword()));
//                memberRepository.merge(m);
//            }
//        }
//        log.info("hotfix -> 기존 패스워드 암호화 완료");
//    }
}
