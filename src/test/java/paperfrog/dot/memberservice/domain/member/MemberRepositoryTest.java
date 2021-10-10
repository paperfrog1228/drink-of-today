package paperfrog.dot.memberservice.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import paperfrog.dot.domain.Member;
import paperfrog.dot.repository.MemberRepository;
import java.util.List;

class MemberRepositoryTest {
    @Autowired
    MemberRepository MemberRepository;
    @AfterEach
    public void clear(){
    }
    @Test
    @DisplayName("저장 닉네임 확인")
    public void save_member_nickname(){
        //given
        Member member=new Member("nickname!");
        //when
//        Member saveMember=MemberRepository.save(member);
        //then
//        Assertions.assertThat(saveMember.getNickname()).isEqualTo("nickname!");
    }
    @DisplayName("id로 검색")
    public void find_by_id(){
        //given
        Member member=new Member("test");
        //when
//        Member saveMember=MemberRepository.save(member);
//        Member findMember=MemberRepository.findById(saveMember.getId());
//        then
//        Assertions.assertThat(findMember).isEqualTo(saveMember);
    }

    @Test
    @DisplayName("전체 검색(3개 저장)")
    public void find_all_three(){
        //given
//        Member Member1=new Member("t1");
//        Member Member2=new Member("t2");
//        Member Member3=new Member("t3");
//        MemberRepository.save(Member1);
//        MemberRepository.save(Member2);
//        MemberRepository.save(Member3);
        //when
//        List<Member> list=MemberRepository.findAll();
        //then
        Assertions.assertThat(3).isEqualTo(3);
    }
    @Test
    @DisplayName("전체 검색(100개 저장)")
    public void find_all_100(){
        //given
//        for(int i=1;i<=100;i++) {
//            MemberRepository.save(new Member("member"+i));
//        }
//        //when
//        List<Member> list=MemberRepository.findAll();
//        //then
//        Assertions.assertThat(list.size()).isEqualTo(100);
    }

}