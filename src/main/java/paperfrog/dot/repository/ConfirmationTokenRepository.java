package paperfrog.dot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import paperfrog.dot.domain.ConfirmationToken;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ConfirmationTokenRepository {
    private final EntityManager em;
    public void save(ConfirmationToken confirmationToken) {
        em.persist(confirmationToken);
    }

    public ConfirmationToken findById(String confirmationTokenId) {
        return em.find(ConfirmationToken.class,confirmationTokenId);
    }
    public void expireToken(String tokenId){

    }
}