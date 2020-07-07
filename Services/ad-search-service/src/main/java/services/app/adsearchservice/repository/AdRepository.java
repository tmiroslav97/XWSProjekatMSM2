package services.app.adsearchservice.repository;


import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import services.app.adsearchservice.model.Ad;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

    Page<Ad> findAllByDeleted(Boolean deleted, Pageable pageable);

    @Query("SELECT ad FROM Ad ad, CarCalendarTerm calendar WHERE calendar.startDate<=(?3) AND calendar.endDate>=(?4) " +
            "AND ad.id = calendar.ad.id AND ad.deleted=(?1) AND ad.location=(?2)")
    Page<Ad> findByDeletedAndLocationAndCarCalendarTermsStartDateBeforeAndCarCalendarTermsEndDateAfter(Boolean deleted, String location, DateTime startDate, DateTime endDate, Pageable pageable);

    @Query("SELECT ad FROM Ad ad, CarCalendarTerm calendar " +
            "WHERE calendar.startDate<=(?3) AND calendar.endDate>=(?4) AND ad.id = calendar.ad.id " +
            "AND ad.deleted=(?1) AND ad.location=(?2) AND ad.car.carManufacturer=(?5) " +
            "AND ad.car.carModel=(?6) AND ad.car.carType=(?7) AND ad.car.mileage<=(?8)  " +
            "AND ((ad.car.distanceLimitFlag='LIMITED' AND ad.car.distanceLimit>=(?9)) OR ad.car.distanceLimitFlag='UNLIMITED') " +
            "AND ad.car.gearboxType=(?10) AND ad.car.fuelType=(?11) AND ad.car.childrenSeatNum=(?12)" +
            "AND ad.car.cdw=(?13) AND ad.pricePerDay>=(?14) AND ad.pricePerDay<=(?15)")
    Page<Ad> findByDeletedAndCarCalendarTermsStartDateBeforeAndCarCalendarTermsEndDateAfter(Boolean deleted, String location, DateTime startDate, DateTime endDate, String carManufacturer,
                                                                                                       String carModel, String carType, Float mileage,
                                                                                                       Float mileageKM, String gearboxType, String fuelType, Integer childrenSeatNum,
                                                                                                       Boolean cdw, Float startPrice, Float endPrice, Pageable pageable);


}
