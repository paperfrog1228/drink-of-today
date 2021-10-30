package paperfrog.dot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import paperfrog.dot.domain.Comment;
import paperfrog.dot.domain.CommentCreateDTO;
import paperfrog.dot.domain.CommentDeleteDTO;
import paperfrog.dot.domain.Member;
import paperfrog.dot.repository.BoardRepository;
import paperfrog.dot.repository.CommentRepository;
import paperfrog.dot.repository.MemberRepository;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    public Long save(CommentCreateDTO commentDTO, Long boardId){
        Comment comment=new Comment();
        comment.setText(commentDTO.getText());
        comment.setBoardId(boardId);
        Member member=memberRepository.findById(commentDTO.getMemberId());
        comment.setWriter(member);
        comment.setNickname(member.getNickname());
        comment.setDate(getNowDate());
        commentRepository.save(comment);
        return comment.getId();
    }
    public void delete(CommentDeleteDTO commentDTO){
        Member member=memberRepository.findById(commentDTO.getMemberId());
        if(commentDTO.getMemberId()!=member.getId())
            return;
        commentRepository.remove(commentDTO.getCommentId());
    }

    private String getNowDate(){
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yy.M.d HH:mm");
        return now.format(dateTimeFormatter);
    }
}
