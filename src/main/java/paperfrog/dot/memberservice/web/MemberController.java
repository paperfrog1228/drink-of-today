package paperfrog.dot.memberservice.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import paperfrog.dot.memberservice.domain.member.Member;
import paperfrog.dot.memberservice.domain.member.MemberRepository;
import paperfrog.dot.memberservice.domain.member.MemberSaveForm;

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
        model.addAttribute("member",new MemberSaveForm());
        return "user/join";
    }
    //todo 복합룰 하나 만들자 아이디 , 비번 같은 글자 3개이상 안되게
    @PostMapping("/join")
    public String join(@Validated @ModelAttribute("member") MemberSaveForm memberForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/user/join";
        }
        Member saveMember=new Member();
        saveMember.setLoginId(memberForm.getLoginId());
        saveMember.setNickname(memberForm.getNickname());
        saveMember.setPassword(memberForm.getPassword());
        memberRepository.save(saveMember);
        log.debug("new member : {}",saveMember);
        return "redirect:/board/list";
    }
    @GetMapping("member_list")
    public String memberList(Model model){
        List<Member> list=memberRepository.findAll();
        model.addAttribute("memberList",list);
        return "member/memberList";
    }

}
