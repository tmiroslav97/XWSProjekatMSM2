package agent.app.dto.sync;

import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdSyncDTO {
    private String name;
    private String location;
    private String distanceLimitFlag;
    private Float distanceLimit;
    private String publishedDate;
    private CarSyncDTO carSyncDTO;
    private Long priceList;
    private List<Long> discountList;
    private List<CarCalendarTermSyncDTO> carCalendarTermSyncDTOList;
}
