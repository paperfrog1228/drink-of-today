package paperfrog.dot.domain;

import lombok.Getter;
import lombok.Setter;
import paperfrog.dot.web.BoardType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Board {
    @Id @GeneratedValue
    private Long id;
    private String writer;
    private String date;
    private String title;
    @Enumerated(EnumType.STRING)
    private BoardType dtype;
    private String content;
    @OneToMany(cascade = CascadeType.ALL)
    private List<UploadFile> imageFiles=new ArrayList<>();

    public Board() {
        dtype=BoardType.NORMAL;
    }
    public Board(BoardForm boardForm){
        dtype=boardForm.getBoardType();
        title= boardForm.getTitle();
        content= boardForm.getContent();
    }
    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

}