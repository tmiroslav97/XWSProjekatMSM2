package agent.app.repository;

import agent.app.model.CarManufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarManufacturerRepository extends JpaRepository<CarManufacturer, Long> {

    Boolean existsByName(String name);
}
