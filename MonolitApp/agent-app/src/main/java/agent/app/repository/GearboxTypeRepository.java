package agent.app.repository;

import agent.app.model.GearboxType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GearboxTypeRepository extends JpaRepository<GearboxType, Long> {

    Boolean existsByName(String name);
}
