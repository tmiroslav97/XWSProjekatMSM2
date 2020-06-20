package agent.app.repository;

import agent.app.model.PublisherUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherUserRepository extends JpaRepository<PublisherUser, Long> {
    Boolean existsByEmail(String email);
    PublisherUser findByEmail(String email);
}
