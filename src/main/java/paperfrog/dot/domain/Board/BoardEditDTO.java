package paperfrog.dot.domain.Board;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BoardEditDTO {
    private String date;
    private String title;
    private String content;
    private ArrayList<MultipartFile> imageFiles=new ArrayList<>();
    private ArrayList<UploadFile> uploadFiles=new ArrayList<>();
}
