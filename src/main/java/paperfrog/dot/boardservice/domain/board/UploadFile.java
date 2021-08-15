package paperfrog.dot.boardservice.domain.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadFile {
    private String uploadFileName;
    private String storeFileName; //서버 저장용 이름

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
