package services.app.carrequestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import services.app.carrequestservice.model.Request;
import services.app.carrequestservice.model.RequestStatusEnum;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllByEndUser(Long id);

    @Query("SELECT req FROM Request req where req.endUser=(?1) and req.status=(?2) order by req.submitDate desc")
    List<Request> findAllByEndUserAndByStatus(Long id, RequestStatusEnum status);

    List<Request> findAllByPublisherUser(Long id);

    @Query("SELECT req FROM Request req where req.publisherUser=(?1) and req.status=(?2) order by req.submitDate desc")
    List<Request> findAllByPublisherUserAndByStatus(Long id, RequestStatusEnum status);
}
