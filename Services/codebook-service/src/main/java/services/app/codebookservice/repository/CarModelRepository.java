package services.app.codebookservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import services.app.codebookservice.model.CarModel;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Long> {

    Boolean existsByName(String name);
}
