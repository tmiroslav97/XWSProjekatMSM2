package services.app.adsearchservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import services.app.adsearchservice.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}
