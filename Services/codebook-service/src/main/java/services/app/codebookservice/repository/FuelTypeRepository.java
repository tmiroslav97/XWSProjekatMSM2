package services.app.codebookservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import services.app.codebookservice.model.FuelType;

@Repository
public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {

    Boolean existsByName(String name);
}
