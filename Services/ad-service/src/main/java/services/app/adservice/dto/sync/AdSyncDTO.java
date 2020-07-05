package services.app.adservice.dto.sync;

import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdSyncDTO {
    private String email;
    private String name;
    private String location;
    private String coverPhoto;
    private Float pricePerDay;
    private String publishedDate;
    private CarSyncDTO carSyncDTO;
    private Long priceList;
    private List<Long> discountList;
    private List<CarCalendarTermSyncDTO> carCalendarTermSyncDTOList;
    private List<String> images;
}
