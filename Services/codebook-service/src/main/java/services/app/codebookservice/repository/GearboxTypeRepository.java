package services.app.codebookservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import services.app.codebookservice.model.GearboxType;

@Repository
public interface GearboxTypeRepository extends JpaRepository<GearboxType, Long> {

    Boolean existsByName(String name);
}
