package paperfrog.dot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import paperfrog.dot.domain.Board;
import paperfrog.dot.domain.Member;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;
    public Long save(Board board){
        em.persist(board);
        return board.getId();
    }

    public Board findById(Long id){
        return em.find(Board.class,id);
    }
    public List<Board> findAll(){
        return em.createQuery("select m from Board m", Board.class)
                .getResultList();
    }
    public void update(Long boardId,Board updateBoard) {
        Board findItem = findById(boardId);
        findItem.setContent(updateBoard.getContent());
        findItem.setTitle(updateBoard.getTitle());
    }
//    public void delete(Long boardId){
//        store.remove(boardId);
//    }
//    public void clear(){
//        store.clear();
//    }
}