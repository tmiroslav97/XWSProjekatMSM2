package agent.app.dto.ad;

import agent.app.dto.car.CarCalendarTermCreateDTO;
import agent.app.dto.car.CarCreateDTO;
import agent.app.dto.pricelist.PriceListCreateDTO;
import lombok.*;

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
