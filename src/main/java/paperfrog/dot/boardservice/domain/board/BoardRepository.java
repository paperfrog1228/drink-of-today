package paperfrog.dot.boardservice.domain.board;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BoardRepository {
    private static final Map<Long,Board> store=new ConcurrentHashMap<>();
    private Long sequence=0L;
    private Date date;
    public void clear(){
        store.clear();
    }
}