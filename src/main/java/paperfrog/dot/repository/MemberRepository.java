package paperfrog.dot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import paperfrog.dot.domain.Member;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;
    public Member save(Member member){
        em.persist(member);
        return member;
    }
    public Member findById(Long id){
        return em.find(Member.class,id);
    }
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    public Optional<Member> findByLoginId(String loginId){
        return findAll().stream().filter(m->m.getLoginId().equals(loginId)).findFirst();
    }
    public void clear(){
//        store.clear();
    }

//    public Member findByNickname(String nickname) {
////        return em.createQuery("select m from Member m where", Member.class);
//    }
}
