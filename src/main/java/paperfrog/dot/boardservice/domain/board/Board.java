package paperfrog.dot.boardservice.domain.board;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Board {
    private Long id;
    Date date;
    private String title;
    private String content;

    public Board(Date date, String title, String content) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.content = content;
    }
}