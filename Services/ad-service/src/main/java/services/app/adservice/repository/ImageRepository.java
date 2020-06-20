package services.app.adservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import services.app.adservice.model.Image;

import java.util.List;


@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("SELECT im FROM Image im WHERE  im.ad.id=(?1)")
    List<Image> findByAdId(Long ad_id);

    @Query("SELECT im FROM Image im WHERE im.name=(?1)")
    Image findByName(String name);
}
