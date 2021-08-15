package paperfrog.dot.boardservice.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import paperfrog.dot.boardservice.domain.board.Board;
import paperfrog.dot.boardservice.domain.board.BoardRepository;
import paperfrog.dot.memberservice.domain.member.Member;
import paperfrog.dot.memberservice.web.SessionConst;


import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {
    private final BoardRepository boardRepository;
    @PostConstruct
    public void testBoard(){
        boardRepository.save(new Board("ttt","ff"));
    }
    @RequestMapping("/list")
    public String boardList(Model model,@SessionAttribute(name= SessionConst.LOGIN_MEMBER,required = false) Member loginMember){
        List<Board> boardList=boardRepository.findAll();
        model.addAttribute("boardList",boardList);
        model.addAttribute("loginMember",loginMember);
        return "board/list";
    }
    @GetMapping("/{boardId}")
    public String board(Model model, @PathVariable long boardId){
        Board board = boardRepository.findById(boardId);
        model.addAttribute("board",board);
        return "board/view/board";
    }
    @GetMapping("/write")
    public String write(Model model){
        model.addAttribute("board",new Board());
        return "board/write";
    }
    @PostMapping("/add")
    public String add(Board board, RedirectAttributes redirectAttributes){
        log.debug("add board : {}",board);
        Board saveBoard=boardRepository.save(board);
        redirectAttributes.addAttribute("boardId",saveBoard.getId());
        redirectAttributes.addAttribute("status",true);
        return "redirect:/board/{boardId}";
    }
    //edit,update
    @GetMapping("/{boardId}/editForm")
    public String editForm(Model model,@PathVariable long boardId){
        Board board=boardRepository.findById(boardId);
        model.addAttribute("board",board);
        return "board/editForm";
    }
    @PostMapping("/{boardId}/edit")
    public String edit(@PathVariable long boardId, Board saveBoard){
        log.debug("success edit boardId : {}",boardId);
        boardRepository.update(boardId,saveBoard);
        return "redirect:/board/list";
    }
}
