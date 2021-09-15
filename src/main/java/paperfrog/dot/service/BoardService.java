package paperfrog.dot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paperfrog.dot.domain.*;
import paperfrog.dot.repository.BoardRepository;
import paperfrog.dot.web.FileStore;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
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
    public boolean delete(Long id,Member member){
        Board deleteBoard=boardRepository.findById(id);
        if(deleteBoard==null) return false;
        if(!deleteBoard.getWriter().equals(member.getNickname())&&member.getMemberGrade()!= MemberGrade.MANAGER) {
            return false;
        }

        boardRepository.delete(deleteBoard);
        return true;
    }
    private ArrayList<UploadFile> getImageFiles(BoardForm boardForm) throws IOException {
        return fileStore.storeFiles(boardForm.getImageFiles());
    }

    private String getNowDate(){
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yy.M.d HH:mm");
        return now.format(dateTimeFormatter);
    }
}
