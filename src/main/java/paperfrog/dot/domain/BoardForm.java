package paperfrog.dot.domain;

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
    @Size(min=1)
    private String title;
    @NotBlank
    @Size(min=1)
    private String content;
    private BoardType boardType;
    private ArrayList<MultipartFile> imageFiles=new ArrayList<>();

    public BoardForm() {
    }

    public BoardForm(BoardType boardType) {
        this.boardType = boardType;
    }
}
