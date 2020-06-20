package services.app.adservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import services.app.adservice.model.Car;

@Repository
public interface CarRepository  extends JpaRepository<Car, Long> {
}
