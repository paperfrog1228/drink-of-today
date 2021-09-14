package paperfrog.dot.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import paperfrog.dot.domain.ConfirmationToken;

import javax.persistence.EntityManager;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ConfirmationTokenRepository {
    private final EntityManager em;
    public void save(ConfirmationToken confirmationToken) {
        em.persist(confirmationToken);
    }

    public ConfirmationToken findById(String confirmationTokenId) {

        UUID uuid=UUID.fromString(confirmationTokenId);
        byte[] uuidBytes = new byte[16];
        ByteBuffer.wrap(uuidBytes)
                .order(ByteOrder.BIG_ENDIAN)
                .putLong(uuid.getMostSignificantBits())
                .putLong(uuid.getLeastSignificantBits());
        return em.find(ConfirmationToken.class, UUID.fromString(confirmationTokenId));
    }

}