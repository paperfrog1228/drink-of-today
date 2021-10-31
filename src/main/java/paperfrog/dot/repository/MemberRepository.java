package paperfrog.dot.repository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import paperfrog.dot.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
@Transactional
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;
    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }
    public Long merge(Member member){
        em.merge(member);
        return member.getId();
    }
    public Member findById(Long id){
        return em.find(Member.class,id);
    }
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    public Member findByLoginId(String loginId){
        List<Member> list=findAll();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getLoginId()==null) continue;
            if(list.get(i).getLoginId().equals(loginId))
                return list.get(i);
        }
        return null;
    }
    public void deleteAll(){
        List<Member> list=findAll();
        for(int i=0;i<list.size();i++){
            em.remove(list.get(i));
        }
    }

    public void emailVerified(Long id){
        Member member=findById(id);
        member.setEmailAuth(true);
    }
}
