package agent.app.repository;

import agent.app.model.Ad;
import agent.app.model.DiscountList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiscountListRepository extends JpaRepository<DiscountList, Long> {

    @Query("SELECT dl FROM DiscountList dl WHERE dl.agent.email=(?1)")
    List<DiscountList> findAllByAgent(String email);
}
