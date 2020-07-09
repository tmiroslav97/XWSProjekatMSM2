package services.app.adservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import services.app.adservice.model.DiscountList;

public interface DiscountListRepository extends JpaRepository<DiscountList, Long> {
}
