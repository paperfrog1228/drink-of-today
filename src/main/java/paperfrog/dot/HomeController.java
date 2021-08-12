package paperfrog.dot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import paperfrog.dot.memberservice.domain.member.Member;
import paperfrog.dot.memberservice.domain.member.MemberRepository;

@Controller
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
   private final MemberRepository memberRepository;
   @GetMapping
   public String homeLogin(Long memberId, Model model) {
      log.debug("memberId : {}",memberId);
      //비로그인
      if (memberId == null) {
         return "home";
      }
      //로그인
      Member loginMember = memberRepository.findById(memberId);
      if (loginMember == null) {
         return "home";
      }
      model.addAttribute("member", loginMember);
      return "loginHome";
   }
}
