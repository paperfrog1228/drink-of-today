package paperfrog.dot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paperfrog.dot.domain.Board;
import paperfrog.dot.domain.BoardForm;
import paperfrog.dot.domain.Member;
import paperfrog.dot.domain.UploadFile;
import paperfrog.dot.repository.BoardRepository;
import paperfrog.dot.web.FileStore;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final FileStore fileStore;
    public Long save(BoardForm boardForm, Member member) throws IOException {
        Board saveBoard=new Board(boardForm);
        saveBoard.setWriter(member.getNickname());
        saveBoard.setDate(getNowDate());
        saveBoard.setImageFiles(getImageFiles(boardForm));
        return boardRepository.save(saveBoard);
    }

    private ArrayList<UploadFile> getImageFiles(BoardForm boardForm) throws IOException {
        return fileStore.storeFiles(boardForm.getImageFiles());
    }

    private String getNowDate(){
        LocalDateTime now=LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yy.M.d hh:mm");
        return now.format(dateTimeFormatter);
    }
}
