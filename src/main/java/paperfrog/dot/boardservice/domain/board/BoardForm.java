package paperfrog.dot.boardservice.domain.board;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Getter
@Setter

public class BoardForm {
    private Long id;
    private String writer;
    private String date;
    private String title;
    private String content;
    private List<MultipartFile> imageFiles;
}
