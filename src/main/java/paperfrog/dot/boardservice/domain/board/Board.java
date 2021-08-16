package paperfrog.dot.boardservice.domain.board;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Board {
    private Long id;
    private String writer;
    private String date;
    private String title;
    private String content;

    private List<UploadFile> imageFiles;
    public Board() {
    }

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

}