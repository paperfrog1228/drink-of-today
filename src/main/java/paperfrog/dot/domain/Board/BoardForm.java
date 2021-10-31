package paperfrog.dot.domain.Board;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Getter
@Setter
public class BoardForm {
    @NotBlank
    @Size(max=20)
    private String title;
    @NotBlank
    @Size(max=250)
    private String content;
    public BoardType boardType;
    private ArrayList<MultipartFile> imageFiles=new ArrayList<>();

    public BoardForm() {
    }

    public BoardForm(BoardType boardType) {
        this.boardType = boardType;
    }
}
