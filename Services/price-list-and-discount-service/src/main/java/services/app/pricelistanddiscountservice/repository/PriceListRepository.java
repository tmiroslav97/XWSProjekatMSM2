package services.app.pricelistanddiscountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import services.app.pricelistanddiscountservice.model.PriceList;

import java.util.List;

@Repository
public interface PriceListRepository extends JpaRepository<PriceList,Long> {

    List<PriceList> findAllByPublisherUser(Long id);
}
