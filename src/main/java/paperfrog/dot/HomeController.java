package paperfrog.dot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import paperfrog.dot.memberservice.domain.member.Member;
import paperfrog.dot.memberservice.domain.member.MemberRepository;
import paperfrog.dot.memberservice.web.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
   private final MemberRepository memberRepository;
   @GetMapping
   public String homeLogin(@SessionAttribute(name=SessionConst.LOGIN_MEMBER,required = false)Member loginMember, Model model) {
      //비로그인(세션 없음)
      if (loginMember == null) {
         return "home";
      }
      //로그인
      model.addAttribute("member", loginMember);
      log.debug("Login Success memberId : {} LoginId : {}",loginMember.getId(),loginMember.getLoginId());
      return "loginHome";
   }
}
