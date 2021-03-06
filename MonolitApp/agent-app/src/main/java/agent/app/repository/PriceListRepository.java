package agent.app.repository;

import agent.app.model.Ad;
import agent.app.model.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceListRepository extends JpaRepository<PriceList, Long> {

    @Query("SELECT pl FROM PriceList pl WHERE pl.publisherUser.email=(?1)")
    List<PriceList> findAllByPublisherUser(String email);
}
