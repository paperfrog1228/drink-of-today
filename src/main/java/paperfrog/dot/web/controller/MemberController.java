package paperfrog.dot.web.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import paperfrog.dot.domain.Member;
import paperfrog.dot.domain.MemberSaveForm;
import paperfrog.dot.repository.MemberRepository;
import paperfrog.dot.service.MemberService;
import paperfrog.dot.web.Login.LoginForm;
import paperfrog.dot.web.SessionConst;
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
    private final MemberRepository memberRepository;
    //TODO : 테스트 계정이니 나중에 꼭 지우자
    @PostConstruct
    public void testMember(){
        Member member=new Member("test입니당");
        member.setLoginId("test3");
        member.setPassword("qqqqqq");
        member.setEmail("paperfrog@naver.com");
        memberRepository.save(member);
    }
    // 회원가입
    @GetMapping("/join")
    public String joinForm(Model model){
        model.addAttribute("member",new MemberSaveForm());
        return "user/join";
    }
    //todo validate 유지보수를 잘 처리하자..
    @PostMapping("/join")
    public String join(@Validated @ModelAttribute("member") MemberSaveForm memberForm
            ,BindingResult bindingResult
            ,RedirectAttributes redirectAttributes) {

        //진짜 최악이다 코드
        bindingResult = memberService.join(memberForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/user/join";
        }
        redirectAttributes.addAttribute("email",memberForm.getEmail());
        return "redirect:/user/invalid_user";
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
            , HttpServletRequest request
            ,RedirectAttributes redirectAttributes) {
        Member loginMember = memberService.login(form.getLoginId(), form.getPassword());
        log.debug("form id : {} , form pw : {} ",form.getLoginId(),form.getPassword());
        if(loginMember==null){
            //Todo: 로그인 실패 처리
            return "/user/login";
        }
        if(!loginMember.isEmailAuth()){
            redirectAttributes.addAttribute("email",loginMember.getEmail());
            return "redirect:/user/invalid_user";
        }
        //세션 처리
        HttpSession session=request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER,loginMember);
        log.debug("loginMember {}",loginMember);
        return "redirect:"+requestURL;
    }
    @GetMapping("invalid_user")
    public String invalidUser(Member member){
        return "user/invalid_user";
    }
    @GetMapping("success_email_auth")
    public String successEmailAuth(Member member){
        return "user/success_email_auth";
    }
    @GetMapping("confirm_email")
    public String emailVerified(@RequestParam("token") String tokenID){
        memberService.confirmEmail(tokenID);
        return "redirect:/user/success_email_auth";
    }
}
