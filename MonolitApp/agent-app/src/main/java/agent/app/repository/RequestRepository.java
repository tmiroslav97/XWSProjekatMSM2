package agent.app.repository;

import agent.app.model.Request;
import agent.app.model.enumeration.RequestStatusEnum;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query("SELECT req FROM Request req where req.publisherUser.email=(?1) and req.status=(?2) order by req.submitDate desc")
    List<Request> findAllByEndUserAndByStatus(String email, RequestStatusEnum status);

    Boolean existsByMainId(Long mainId);

    Request findByMainId(Long mainId);

    @Query("SELECT req FROM Request req join req.ads ad where req.id<>(?1) and ad.adId=(?2) and ad.startDate<=(?4) and ad.endDate>=(?3)")
    List<Request> findAllRequestContainsAdAndOverlapDate(Long requestId, Long adId, DateTime startDate, DateTime endDate);
}
