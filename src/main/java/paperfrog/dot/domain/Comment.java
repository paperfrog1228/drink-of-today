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
    private String writer;
    private String date;
    private String text;
    @ManyToOne(cascade = CascadeType.ALL) //단방향 해도 괜찮지 않을까?
    private Board board;
}
