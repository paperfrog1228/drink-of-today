package paperfrog.dot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import paperfrog.dot.domain.Comment;
import paperfrog.dot.repository.BoardRepository;
import paperfrog.dot.repository.CommentRepository;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    public Long save(Comment comment,Long boardId){
        comment.setBoard(boardRepository.findById(boardId));
        commentRepository.save(comment);
        return comment.getId();
    }
}
