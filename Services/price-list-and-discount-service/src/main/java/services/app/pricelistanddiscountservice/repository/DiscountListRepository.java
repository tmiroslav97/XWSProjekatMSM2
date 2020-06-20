package services.app.pricelistanddiscountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import services.app.pricelistanddiscountservice.model.DiscountList;

@Repository
public interface DiscountListRepository extends JpaRepository<DiscountList,Long> {
}
