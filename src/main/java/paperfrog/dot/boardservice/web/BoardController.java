package paperfrog.dot.boardservice.web;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import paperfrog.dot.boardservice.domain.board.Board;
import paperfrog.dot.boardservice.domain.board.BoardRepository;


import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardRepository boardRepository;

    @PostConstruct
    public void addTestBoard(){
        for(int i=1;i<=100;i++) {
            Date date = new Date();
            boardRepository.save(new Board(date, "title"+i, "content"+i));
        }
    }
    @RequestMapping("/list")
    public String boardList(Model model){
        List<Board> boardList=boardRepository.findAll();
        model.addAttribute("boardList",boardList);
        return "board/boardlist";
    }
    @GetMapping("/{boardId}")
    public String item(Model model, @PathVariable long boardId){
        Board board = boardRepository.findById(boardId);
        model.addAttribute("board",board);
        return "board/view/board";
    }
}
