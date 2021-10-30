package paperfrog.dot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import paperfrog.dot.domain.Board.Board;
import paperfrog.dot.domain.Board.BoardEditDTO;
import paperfrog.dot.domain.Board.BoardType;

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
    public Long update(Long boardId, BoardEditDTO updateBoard) {
        Board findBoard = findById(boardId);
        findBoard.update(updateBoard);
        return boardId;
    }
    public List<Board> findListByDtype(BoardType boardType){
        return em.createQuery("select m from Board m where m.dtype= :boardType", Board.class)
                .setParameter("boardType",boardType)
                .getResultList();
    }
    public boolean delete(Board board){
        em.remove(board);
        em.flush();
        return true;
    }

    public void deleteAll(){
        List<Board> list=findAll();
        for(int i=0;i<list.size();i++){
            em.remove(list.get(i));
        }
    }
}