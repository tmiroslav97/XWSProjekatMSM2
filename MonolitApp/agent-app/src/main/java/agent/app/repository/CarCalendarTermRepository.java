package agent.app.repository;

import agent.app.model.CarCalendarTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarCalendarTermRepository extends JpaRepository<CarCalendarTerm, Long> {
}
