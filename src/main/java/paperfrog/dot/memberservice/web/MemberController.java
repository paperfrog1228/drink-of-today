package paperfrog.dot.memberservice.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import paperfrog.dot.boardservice.domain.board.Board;
import paperfrog.dot.memberservice.domain.member.Member;
import paperfrog.dot.memberservice.domain.member.MemberRepository;

import java.util.List;

@Controller
@RequestMapping("/member/")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberRepository memberRepository;
    @GetMapping("join_new")
    public String addForm(Model model){
        model.addAttribute("member",new Member());
        return "member/addMemberForm";
    }
    @GetMapping("member_list")
    public String memberList(Model model){
        List<Member> list=memberRepository.findAll();
        model.addAttribute("memberList",list);
        return "member/memberList";
    }
    @PostMapping("/add")
    public String add(Member member, RedirectAttributes redirectAttributes){
        Member save_member=memberRepository.save(member);
        //redirectAttributes.addAttribute("boardId",save_member.getId());
        //redirectAttributes.addAttribute("status",true);
        return "redirect:/";
    }
}
