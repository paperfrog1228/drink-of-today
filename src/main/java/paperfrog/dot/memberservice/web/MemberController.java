package paperfrog.dot.memberservice.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import paperfrog.dot.memberservice.domain.member.Member;
import paperfrog.dot.memberservice.domain.member.MemberRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberRepository memberRepository;

    @PostConstruct
    public void testMember(){
        Member member=new Member("test입니당");
        member.setLoginId("test");
        member.setPassword("qqq");
        memberRepository.save(member);
    }
    // 회원가입
    @GetMapping("/join")
    public String joinForm(Model model){
        model.addAttribute("member",new Member());
        return "user/join";
    }
    @PostMapping("/join")
    public String join(Member member, RedirectAttributes redirectAttributes){
        Member save_member=memberRepository.save(member);
        log.debug("new member : {}",save_member);
        return "redirect:/";
    }
    @GetMapping("member_list")
    public String memberList(Model model){
        List<Member> list=memberRepository.findAll();
        model.addAttribute("memberList",list);
        return "member/memberList";
    }

}
