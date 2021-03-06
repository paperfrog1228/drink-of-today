package paperfrog.dot.domain.Board;

import lombok.Getter;
import lombok.Setter;
import paperfrog.dot.domain.Board.Board;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class UploadFile {
    @Id
    @GeneratedValue
    private Long id;
    private String uploadFileName;
    private String storeFileName; //서버 저장용 이름

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name="board_id")
    private Board board;
    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
    public UploadFile(){};
}
