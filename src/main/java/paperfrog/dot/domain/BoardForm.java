package paperfrog.dot.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class BoardForm {
    @NotBlank
    @Size(min=1)
    private String title;
    @NotBlank
    @Size(min=1)
    private String content;
    private ArrayList<MultipartFile> imageFiles=new ArrayList<>();
}
