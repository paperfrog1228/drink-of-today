package paperfrog.dot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import paperfrog.dot.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.Id;

@Repository
@Transactional
@RequiredArgsConstructor
public class CommentRepository {
    private final EntityManager em;
    public Long save(Comment comment) {
        em.persist(comment);
        return comment.getId();
    }
}
