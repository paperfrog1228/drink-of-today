package paperfrog.dot.boardservice.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import paperfrog.dot.boardservice.domain.board.Board;
import paperfrog.dot.boardservice.domain.board.BoardForm;
import paperfrog.dot.boardservice.domain.board.BoardRepository;
import paperfrog.dot.boardservice.domain.board.UploadFile;
import paperfrog.dot.boardservice.file.FileStore;
import paperfrog.dot.memberservice.domain.member.Member;
import paperfrog.dot.memberservice.domain.member.MemberSaveForm;
import paperfrog.dot.memberservice.web.SessionConst;


import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {
    private final BoardRepository boardRepository;
    private final FileStore fileStore;
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
    //read,view
    @GetMapping("/{boardId}")
    public String board(Model model, @PathVariable long boardId,@SessionAttribute(name= SessionConst.LOGIN_MEMBER,required = false) Member loginMember){
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
//    @GetMapping("/attach/{boardId}")
//    public ResponseEntity<Resource> downloadAttach(@PathVariable Long boardId) throws MalformedURLException {
//        Board board=boardRepository.findById(boardId);
//        String storeFileName=board.getAttachFile().getStoreFileName();
//        String uploadFileName=board.getAttachFile().getUploadFileName();
//        UrlResource resource = new UrlResource("file:"+fileStore.getFullPath(storeFileName));
//        String contentDisposition="attachment; filename=\""+uploadFileName+"\"";
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition).body(resource)
//    }
    //create,write,add
    @GetMapping("/write")
    public String writeForm(Model model,@SessionAttribute(name= SessionConst.LOGIN_MEMBER,required = false) Member loginMember){
        model.addAttribute("board",new BoardForm());
        model.addAttribute("loginMember",loginMember);
        return "board/writeForm";
    }
    @PostMapping("/add")
    public String add(@Validated @ModelAttribute("board") BoardForm boardForm, BindingResult bindingResult,
                      @SessionAttribute(name= SessionConst.LOGIN_MEMBER,required = false) Member loginMember,
                      RedirectAttributes redirectAttributes) throws IOException {
        if(bindingResult.hasErrors()){
            return "/board/writeForm";
        }
        Board board=new Board();
        board.setImageFiles(fileStore.storeFiles(boardForm.getImageFiles()));
        //todo: 아 optional 고쳐줘야함 피곤
        if(loginMember!=null)
        board.setWriter(loginMember.getNickname());
        board.setContent(boardForm.getContent());
        board.setTitle(boardForm.getTitle());
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
    //delete
    @GetMapping("/{boardId}/delete")
    public String delete(@PathVariable long boardId){
        boardRepository.delete(boardId);
        return "redirect:/board/list";
    }
}
