package paperfrog.dot.web.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import paperfrog.annotation.argumentresolver.Login;
import paperfrog.dot.domain.Board;
import paperfrog.dot.domain.BoardForm;
import paperfrog.dot.repository.BoardRepository;
import paperfrog.dot.domain.Member;
import paperfrog.dot.service.BoardService;
import paperfrog.dot.web.FileStore;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;
    private final FileStore fileStore;
    private final BoardRepository boardRepository;
    @RequestMapping("/list")
    public String boardList(Model model, @Login Member loginMember){
        List<Board> boardList=boardRepository.findAll();
        model.addAttribute("boardList",boardList);
        model.addAttribute("loginMember",loginMember);
        return "board/list";
    }
    //read,view
    @GetMapping("/{boardId}")
    public String board(Model model, @PathVariable long boardId,@Login Member loginMember){
        Board board = boardRepository.findById(boardId);
        model.addAttribute("board",board);
        model.addAttribute("loginMember",loginMember);
        return "board/view/board";
    }
    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:"+fileStore.getFullPath(filename));
    }

    //create,write,add
    @GetMapping("/write")
    public String writeForm(Model model,@Login Member loginMember){
        model.addAttribute("board",new BoardForm());
        model.addAttribute("loginMember",loginMember);
        return "board/writeForm";
    }
    @PostMapping("/add")
    public String add(@Validated @ModelAttribute("board") BoardForm boardForm, BindingResult bindingResult,
                      @Login Member loginMember,
                      RedirectAttributes redirectAttributes) throws IOException {
        if(bindingResult.hasErrors()){
            return "/board/writeForm";
        }
        Long saveBoardId=boardService.save(boardForm,loginMember);
        redirectAttributes.addAttribute("boardId",saveBoardId);
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
    //delete
//    @GetMapping("/{boardId}/delete")
//    public String delete(@PathVariable long boardId){
//        boardRepository.delete(boardId);
//        return "redirect:/board/list";
//    }
}
