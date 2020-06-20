package services.app.adservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import services.app.adservice.model.Comment;

@Repository
public interface CommentRepository  extends JpaRepository<Comment, Long> {
}
