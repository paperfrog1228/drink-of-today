package paperfrog.dot.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import paperfrog.dot.domain.Comment;
import paperfrog.dot.domain.CommentCreateDTO;
import paperfrog.dot.repository.BoardRepository;
import paperfrog.dot.service.BoardService;
import paperfrog.dot.service.CommentService;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping("board/{boardType}/{boardId}/comment")
    public String addComment(CommentCreateDTO comment, @PathVariable String boardType, @PathVariable Long boardId){
        System.out.println(comment.getWriterId());
        commentService.save(comment,boardId);
        return "board/list";
    }

}
