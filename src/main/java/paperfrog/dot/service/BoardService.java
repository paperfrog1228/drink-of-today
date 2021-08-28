package paperfrog.dot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paperfrog.dot.domain.Board;
import paperfrog.dot.domain.BoardForm;
import paperfrog.dot.repository.BoardRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Transactional
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public Board save(BoardForm boardForm){
        Board saveBoard=new Board(boardForm);
        saveBoard.setDate(getNowDate());
        return boardRepository.save(saveBoard);
    }
    private String getNowDate(){
        LocalDateTime now=LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.M.d hh:mm:ss");
        return now.format(dateTimeFormatter);
    }
}
