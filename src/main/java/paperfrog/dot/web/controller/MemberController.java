package paperfrog.dot.web.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paperfrog.dot.domain.Member;
import paperfrog.dot.domain.MemberSaveForm;
import paperfrog.dot.service.MemberService;
import paperfrog.dot.web.Login.LoginForm;
import paperfrog.dot.web.SessionConst;
import paperfrog.dot.web.MemberValidator;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
//        memberService.join(member);
    }
    // 회원가입
    @GetMapping("/join")
    public String joinForm(Model model){
        model.addAttribute("member",new MemberSaveForm());
        return "user/join";
    }
    //todo validate 유지보수를 잘 처리하자..
    @PostMapping("/join")
    public String join(@Validated @ModelAttribute("member") MemberSaveForm memberForm,BindingResult bindingResult) {
        Member saveMember = new Member(memberForm);
        //진짜 최악이다 코드
        bindingResult = memberService.join(saveMember, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/user/join";
        }
        log.debug("new member : {} member id : {}",saveMember,saveMember.getPassword());
        return "redirect:/board/list";
    }
    @GetMapping("member_list")
    public String memberList(Model model){
        List<Member> list=memberService.findAll();
        model.addAttribute("memberList",list);
        return "member/memberList";
    }
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm){
        return "user/login";
    }

    @PostMapping("/login")
    public String login(LoginForm form
            , @RequestParam(defaultValue = "/") String requestURL
            , HttpServletRequest request){

        Member loginMember = memberService.login(form.getLoginId(), form.getPassword());
        log.debug("form id : {} , form pw : {} ",form.getLoginId(),form.getPassword());
        if(loginMember==null){
            //Todo: 로그인 실패 처리
            return "/user/login";
        }
        //세션 처리
        HttpSession session=request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER,loginMember);
        log.debug("loginMember {}",loginMember);
        return "redirect:"+requestURL;
    }

}
