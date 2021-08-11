package paperfrog.dot.memberservice.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import paperfrog.dot.boardservice.domain.board.Board;
import paperfrog.dot.memberservice.domain.member.Member;
import paperfrog.dot.memberservice.domain.member.MemberRepository;

@Controller
@RequestMapping("/member/")
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    @GetMapping("join_new")
    public String addForm(){
        return "member/addMemberForm";
    }
    @GetMapping("member_list")
    public String memberList(){
        return "member/memberList";
    }
    @PostMapping("/add")
    public String add(Member member, RedirectAttributes redirectAttributes){
        Member save_member=memberRepository.save(member);
        //redirectAttributes.addAttribute("boardId",save_member.getId());
        //redirectAttributes.addAttribute("status",true);
        return "redirect:/member/memberList}";
    }
}
