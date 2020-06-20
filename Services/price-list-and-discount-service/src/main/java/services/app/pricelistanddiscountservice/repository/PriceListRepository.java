package services.app.pricelistanddiscountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import services.app.pricelistanddiscountservice.model.PriceList;

@Repository
public interface PriceListRepository extends JpaRepository<PriceList,Long> {

}
