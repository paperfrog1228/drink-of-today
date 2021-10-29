package paperfrog.dot.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import paperfrog.dot.domain.Comment;
import paperfrog.dot.domain.CommentCreateDTO;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CommentServiceTest {
    HashMap<Long,Comment> commentHashMap=new HashMap<>();
    @BeforeEach
    public void clearMap(){
        commentHashMap.clear();
    }

    @Test
    @DisplayName("저장 텍스트 확인")
    public void text_correspond() {
        //given
        String testText="test@tfshekhskuhfsddxx";
        CommentCreateDTO commentCreateDTO=new CommentCreateDTO();
        commentCreateDTO.setText(testText);
        Long commentId=10L;
        Comment comment=new Comment();
        comment.setText(commentCreateDTO.getText());
        //when
        commentHashMap.put(commentId,comment);
        Comment expectComment=commentHashMap.get(commentId);
        //then
        Assertions.assertThat(testText).isEqualTo(expectComment.getText());
    }

}