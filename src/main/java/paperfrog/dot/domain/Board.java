package paperfrog.dot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    @OneToMany
    private List<UploadFile> imageFiles;

    public Board() {
    }

    public Board(BoardForm boardForm){
        title= boardForm.getTitle();
        content= boardForm.getContent();
    }
    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

}