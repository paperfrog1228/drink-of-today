package paperfrog.dot.boardservice.domain.board;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BoardRepository {
    private static final Map<Long,Board> store=new ConcurrentHashMap<>();
    private Long sequence=0L;
    private Date date;
    public Board save(Board board){
        board.setId(++sequence);
        date=new Date();
        board.setDate(date);
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
    public void clear(){
        store.clear();
    }
}