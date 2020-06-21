package services.app.adservice.repository;


import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import services.app.adservice.model.CarCalendarTerm;

@Repository
public interface CarCalendarTermRepository extends JpaRepository<CarCalendarTerm, Long> {

    @Query("select cct from CarCalendarTerm cct where cct.ad.id=(?1) and cct.startDate<=(?2) and cct.endDate>=(?3)")
    CarCalendarTerm findByAdAndDate(Long id, DateTime startDate, DateTime endDate);
}
