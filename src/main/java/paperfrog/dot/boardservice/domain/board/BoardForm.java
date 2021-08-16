package paperfrog.dot.boardservice.domain.board;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;
@Getter
@Setter
public class BoardForm {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private List<MultipartFile> imageFiles;
}
