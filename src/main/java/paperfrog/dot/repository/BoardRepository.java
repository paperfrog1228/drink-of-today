package paperfrog.dot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import paperfrog.dot.domain.Board;
import paperfrog.dot.domain.BoardType;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
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
    public List<Board> findListByDtype(BoardType boardType){
        return em.createQuery("select m from Board m where m.dtype= :boardType", Board.class)
                .setParameter("boardType",boardType)
                .getResultList();
    }
    public boolean delete(Board board){
        em.remove(board);
        return true;
    }
}