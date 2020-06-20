package agent.app.repository;

import agent.app.model.CarType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarTypeRepository extends JpaRepository<CarType, Long> {

    Boolean existsByName(String name);
}
