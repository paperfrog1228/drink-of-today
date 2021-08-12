package paperfrog.dot.memberservice.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import paperfrog.dot.memberservice.domain.login.LoginService;
import paperfrog.dot.memberservice.domain.member.Member;
import paperfrog.dot.memberservice.web.Login.LoginForm;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm){
        return "login/loginForm";
    }
    @PostMapping("/login")
    public String login(LoginForm form, RedirectAttributes redirectAttributes){
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        if(loginMember==null){
            //Todo: 로그인 실패 처리
        }
        //Todo: 로그인 성공 처리
        return "redirect:/board/list";
    }

}
