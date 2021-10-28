package paperfrog.dot.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import paperfrog.dot.domain.Comment;
import paperfrog.dot.domain.CommentCreateDTO;
import paperfrog.dot.repository.BoardRepository;
import paperfrog.dot.repository.CommentRepository;
import paperfrog.dot.service.BoardService;
import paperfrog.dot.service.CommentService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CommentRepository commentRepository;
    @PostMapping("board/{boardType}/{boardId}/comment")
    public String addComment(Model model, CommentCreateDTO comment, @PathVariable String boardType, @PathVariable Long boardId){
        commentService.save(comment,boardId);
        List<Comment> list=commentRepository.findByBoardId(boardId);
        val nlString = System.getProperty("line.separator").toString();
        model.addAttribute("commentList",list);
        model.addAttribute("nlString",nlString);
        return "board/view/board :: #commentTable";
    }
}
