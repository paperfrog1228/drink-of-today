package paperfrog.dot.memberservice.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import paperfrog.dot.memberservice.domain.login.LoginService;
import paperfrog.dot.memberservice.domain.member.Member;
import paperfrog.dot.memberservice.web.Login.LoginForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    @GetMapping("/user/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm){
        return "user/login";
    }

    @PostMapping("/user/login")
    public String login(LoginForm form
                        ,@RequestParam(defaultValue = "/") String requestURL
                        ,HttpServletRequest request){

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
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
