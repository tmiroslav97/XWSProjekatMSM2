package services.app.adservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import services.app.adservice.model.CarCalendarTerm;

@Repository
public interface CarCalendarTermRepository extends JpaRepository<CarCalendarTerm, Long> {
}
