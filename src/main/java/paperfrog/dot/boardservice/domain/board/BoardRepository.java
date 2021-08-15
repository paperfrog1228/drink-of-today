package paperfrog.dot.boardservice.domain.board;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Repository
public class BoardRepository {
    private static final Map<Long,Board> store=new ConcurrentHashMap<>();
    private Long sequence=0L;
    private Date date;
    public Board save(Board board){
        board.setId(++sequence);
        LocalDateTime now=LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.M.d hh:mm:ss");
        board.setDate(now.format(dateTimeFormatter));
        store.put(board.getId(), board);
        return board;
    }
    public Board findById(Long id){
        return store.get(id);
    }
    public List<Board> findAll(){
        List<Board> list=new ArrayList<Board>(store.values());
        return list;
    }
    public void update(Long boardId,Board updateBoard) {
        Board findItem = findById(boardId);
        findItem.setContent(updateBoard.getContent());
        findItem.setTitle(updateBoard.getTitle());
    }
    public void delete(Long boardId){
        store.remove(boardId);
    }
    public void clear(){
        store.clear();
    }
}