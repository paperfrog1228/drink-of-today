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
@Controller
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
   private final MemberRepository memberRepository;
   @GetMapping
   public String homeLogin(@SessionAttribute(name=SessionConst.LOGIN_MEMBER,required = false)Member loginMember,
                           Model model,
                           HttpServletRequest request) {
      model.addAttribute("loginMember", loginMember);
      //비로그인(세션 없음)
      if (loginMember == null) {
         log.debug("비회원 접근 : {}",loginMember);
        return "/board/list";
      }
      //로그인
      model.addAttribute(SessionConst.LOGIN_MEMBER,loginMember);
      log.debug("Login Success memberId : {} LoginId : {}",loginMember.getId(),loginMember.getLoginId());
      return "/board/list";
   }
}
