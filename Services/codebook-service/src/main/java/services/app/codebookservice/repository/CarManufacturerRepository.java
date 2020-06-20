package services.app.codebookservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import services.app.codebookservice.model.CarManufacturer;

@Repository
public interface CarManufacturerRepository extends JpaRepository<CarManufacturer, Long> {

    Boolean existsByName(String name);
}
