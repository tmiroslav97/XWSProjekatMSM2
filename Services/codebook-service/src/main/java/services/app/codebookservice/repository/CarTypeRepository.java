package services.app.codebookservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import services.app.codebookservice.model.CarType;

public interface CarTypeRepository extends JpaRepository<CarType, Long> {

    Boolean existsByName(String name);
}
