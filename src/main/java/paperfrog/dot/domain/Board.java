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
    private String content;
    @Enumerated(EnumType.STRING)
    private BoardType dtype;
    @OneToMany(cascade = CascadeType.ALL)
    private List<UploadFile> imageFiles=new ArrayList<>();

    public Board() {
    }

    public Board(BoardForm boardForm){
        dtype=BoardType.NORMAL;
        title= boardForm.getTitle();
        content= boardForm.getContent();
    }
    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

}