package paperfrog.dot.memberservice.domain.member;

import paperfrog.dot.boardservice.domain.board.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemberRepository {
    private static final Map<Long, Member> store=new ConcurrentHashMap<>();
    private Long sequence=0L;
    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    public Member findById(Long id){
        return store.get(id);
    }
    public List<Member> findAll(){
        List<Member> list;
        list = new ArrayList<Member>(store.values());
        return list;
    }
    public void clear(){
        store.clear();
    }
}
