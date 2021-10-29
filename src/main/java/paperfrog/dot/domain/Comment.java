package paperfrog.dot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
    댓글 기능
 **/
@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    private Member writer;
    private String nickname;
    private String date;
    private String text;
    private Long boardId;


}
