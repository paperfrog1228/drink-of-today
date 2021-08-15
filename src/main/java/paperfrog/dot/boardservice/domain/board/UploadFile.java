package paperfrog.dot.boardservice.domain.board;

public class UploadFile {
    private String uploadFileName;
    private String storeFileName; //서버 저장용 이름

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
