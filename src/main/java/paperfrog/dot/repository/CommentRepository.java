package paperfrog.dot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import paperfrog.dot.domain.Comment;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class CommentRepository {
    private final EntityManager em;
    public Long save(Comment comment) {
        em.persist(comment);
        return comment.getId();
    }
    public Comment findByCommentId(Long commentId){
        return em.find(Comment.class,commentId);
    }
    public List<Comment> findByBoardId(Long boardId){
            return em.createQuery("select m from Comment m where boardId = ?1", Comment.class)
                    .setParameter(1,boardId)
                    .getResultList();
    }

    public void remove(Long commentId) {
        em.remove(findByCommentId(commentId));
    }
}
