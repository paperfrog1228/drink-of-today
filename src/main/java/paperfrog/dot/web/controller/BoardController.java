package paperfrog.dot.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import paperfrog.dot.domain.BoardType;
import paperfrog.dot.web.FileStore;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BoardController {
    private final BoardService boardService;
    private final FileStore fileStore;
    private final BoardRepository boardRepository;
    @PostConstruct
    public void test(){

    }
    @RequestMapping("/list")
    public String boardList(Model model, @Login Member loginMember){
        List<Board> boardList=boardRepository.findListByDtype(BoardType.NORMAL);
        Collections.reverse(boardList);
        model.addAttribute("boardList",boardList);
        model.addAttribute("loginMember",loginMember);
        return "board/list";
    }
    //read,view
    @GetMapping("/{boardType}/{boardId}")
    public String board(Model model, @PathVariable long boardId,@Login Member loginMember){
        Board board = boardRepository.findById(boardId);
        val nlString = System.getProperty("line.separator").toString();
        model.addAttribute("board",board);
        model.addAttribute("loginMember",loginMember);
        model.addAttribute("nlString",nlString);
        return "board/view/board";
    }
    @GetMapping("/notice")
    public String noticeList(Model model,@Login Member loginMember){
        List<Board> boardList=boardRepository.findListByDtype(BoardType.NOTICE);
        Collections.reverse(boardList);
        model.addAttribute("noticeList",boardList);
        model.addAttribute("loginMember",loginMember);
        return "board/notice_list";
    }
    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:"+fileStore.getFullPath(filename));
    }

    //create,write,add
    @GetMapping("/write/{boardType}")
    public String writeForm(@PathVariable BoardType boardType, Model model,@Login Member loginMember){
        BoardForm form = new BoardForm(boardType);
        model.addAttribute("board",form);
        model.addAttribute("loginMember",loginMember);
        return "board/writeForm";
    }
    @PostMapping("/add/{boardType}")
    public String add(@PathVariable BoardType boardType
            ,@Validated @ModelAttribute("board") BoardForm boardForm
            ,BindingResult bindingResult
            ,@Login Member loginMember
            ,RedirectAttributes redirectAttributes) throws IOException {

        if(bindingResult.hasErrors()){
            return "board/writeForm";
        }

        Long saveBoardId=boardService.save(boardForm,loginMember);
        redirectAttributes.addAttribute("boardId",saveBoardId);
        redirectAttributes.addAttribute("status",true);
        redirectAttributes.addAttribute("boardType",boardType);
        return "redirect:/board/{boardType}/{boardId}";
//        return "redirect:/list";
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
