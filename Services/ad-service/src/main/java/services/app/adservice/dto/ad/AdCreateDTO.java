package services.app.adservice.dto.ad;

import lombok.*;
import services.app.adservice.dto.car.CarCalendarTermCreateDTO;
import services.app.adservice.dto.car.CarCreateDTO;
import services.app.adservice.dto.pricelist.PriceListCreateDTO;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AdCreateDTO {

    private String name;
    private String location;
    private String distanceLimitFlag;
    private Float distanceLimit;
    private String coverPhoto;
    private List<String> imagesDTO; //lista naziva slika
    private CarCreateDTO carCreateDTO;
    private PriceListCreateDTO priceListCreateDTO;
    private List<CarCalendarTermCreateDTO> carCalendarTermCreateDTOList;

}
