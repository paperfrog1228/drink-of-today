package paperfrog.dot.domain.Board;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BoardEditDTO {
    private String date;
    @NotBlank
    @Size(max=20)
    private String title;
    @NotBlank
    @Size(max=250)
    private String content;
    private ArrayList<MultipartFile> imageFiles=new ArrayList<>();
    private ArrayList<UploadFile> uploadFiles=new ArrayList<>();
}
