package services.app.pricelistanddiscountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import services.app.pricelistanddiscountservice.model.DiscountList;

import java.util.List;

@Repository
public interface DiscountListRepository extends JpaRepository<DiscountList,Long> {

    @Query("SELECT dl FROM DiscountList dl WHERE dl.agent=(?1)")
    List<DiscountList> findAllByAgent(Long agentId);

}
