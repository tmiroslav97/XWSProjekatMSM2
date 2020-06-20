package services.app.adsearchservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import services.app.adsearchservice.model.CarCalendarTerm;

@Repository
public interface CarCalendarTermRepository extends JpaRepository<CarCalendarTerm, Long> {
}
