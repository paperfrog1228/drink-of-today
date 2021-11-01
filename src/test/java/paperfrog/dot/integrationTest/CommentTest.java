package paperfrog.dot.integrationTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import paperfrog.dot.IntegrationTest;
import paperfrog.dot.domain.*;
import paperfrog.dot.domain.Board.BoardForm;
import paperfrog.dot.domain.Board.BoardType;
import paperfrog.dot.repository.BoardRepository;
import paperfrog.dot.repository.CommentRepository;
import paperfrog.dot.repository.MemberRepository;
import paperfrog.dot.service.BoardService;
import paperfrog.dot.service.CommentService;
import paperfrog.dot.service.MemberService;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Rollback
public class CommentTest extends IntegrationTest {
    @Autowired
    CommentService commentService;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BoardService boardService;
    @Autowired
    BoardRepository boardRepository;

    public Long CreateMember() throws NoSuchAlgorithmException {
        MemberSaveForm memberSaveForm = new MemberSaveForm();
        memberSaveForm.setPassword("hfdsfds");
        memberSaveForm.setEmail("paperfrog33@naver.com");
        memberSaveForm.setEmailConfirm("paperfrog33@naver.com");
        memberSaveForm.setNickname("sseess");
        memberSaveForm.setLoginId("fdsfds");
        return memberService.join(memberSaveForm);
    }

    public Long CreateBoard(Member member) throws NoSuchAlgorithmException, IOException {
        BoardForm boardForm = new BoardForm();
        boardForm.setBoardType(BoardType.NORMAL);
        boardForm.setContent("어쩌구 저쩌구");
        boardForm.setTitle("제목!!");
        return boardService.save(boardForm, member);
    }

    @Test
    @DisplayName("댓글 번호로 찾아서 비교")
    public void save_comment() throws NoSuchAlgorithmException, IOException {
        //given
        Long memberId=CreateMember();
        Member member=memberRepository.findById(memberId);
        Long boardId = CreateBoard(member);
        System.out.println("멤버 아이디 : "+memberId);
        CommentCreateDTO commentCreateDTO = new CommentCreateDTO();
        String testText = "fffff";
        commentCreateDTO.setText(testText);
        commentCreateDTO.setMemberId(memberId);

        //when
        Long commentId = commentService.save(commentCreateDTO, boardId);
        Comment expectComment = commentRepository.findByCommentId(commentId);

        //then
        Assertions.assertThat(testText).isEqualTo(expectComment.getText());
    }
    @Test
    @DisplayName("댓글 삭제 테스트")
    public void delete_comment() throws NoSuchAlgorithmException, IOException {
        //given
        Long memberId=CreateMember();
        Member member=memberRepository.findById(memberId);
        Long boardId = CreateBoard(member);
        System.out.println("멤버 아이디 : "+memberId);
        CommentCreateDTO commentCreateDTO = new CommentCreateDTO();
        String testText = "fffff";
        commentCreateDTO.setText(testText);
        commentCreateDTO.setMemberId(memberId);

        //when
        Long commentId = commentService.save(commentCreateDTO, boardId);

        CommentDeleteDTO commentDeleteDTO=new CommentDeleteDTO();
        commentDeleteDTO.setCommentId(commentId);
        commentDeleteDTO.setMemberId(memberId);
        commentService.delete(commentDeleteDTO);

        Comment expectComment = commentRepository.findByCommentId(commentId);

        //then
        org.junit.jupiter.api.Assertions.assertNull(expectComment);
    }



}
