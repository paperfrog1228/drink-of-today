package paperfrog.dot.web;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import paperfrog.dot.domain.Member;
import paperfrog.dot.repository.MemberRepository;
import paperfrog.dot.domain.MemberSaveForm;
import paperfrog.dot.service.MemberService;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    //TODO : 테스트 계정이니 나중에 꼭 지우자
    @PostConstruct
    public void testMember(){
        Member member=new Member("test입니당");
        member.setLoginId("test");
        member.setPassword("qqq");
        memberService.join(member);
    }
    // 회원가입
    @GetMapping("/join")
    public String joinForm(Model model){
        model.addAttribute("member",new MemberSaveForm());
        return "user/join";
    }
    //todo 복합룰 하나 만들자 아이디 , 비번 같은 글자 3개이상 안되게
    @PostMapping("/join")
    public String join(@Validated @ModelAttribute("member") MemberSaveForm memberForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/user/join";
        }
        Member saveMember=new Member(memberForm);
        Long saveMemberId=memberService.join(saveMember);
        log.debug("new member : {} member id : {}",saveMember,saveMemberId);
        return "redirect:/board/list";
    }
    @GetMapping("member_list")
    public String memberList(Model model){
        List<Member> list=memberService.findAll();
        model.addAttribute("memberList",list);
        return "member/memberList";
    }

}
