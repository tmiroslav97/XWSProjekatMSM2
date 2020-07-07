package services.app.carrequestservice.repository;

import org.bouncycastle.cert.ocsp.Req;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import services.app.carrequestservice.model.Ad;
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

    @Query("SELECT req FROM Request req where req.ads in (?1)")
    List<Request> findRequestByAds(List<Ad> ads);

    @Query("SELECT req FROM Request req join req.ads ad where req.id<>(?1) and ad.mainId=(?2) and ad.startDate<=(?4) and ad.endDate>=(?3)")
    List<Request> findAllRequestContainsAdAndOverlapDate(Long requestId, Long mainId, DateTime startDate, DateTime endDate);

    @Query("SELECT req FROM Request req where req.status=(?1) and req.submitDate<=(?2)")
    List<Request> findAllByStatusAndSubmitDate(RequestStatusEnum requestStatusEnum, DateTime submitDateTime);
}
